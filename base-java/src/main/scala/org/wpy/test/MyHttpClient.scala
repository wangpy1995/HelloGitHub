package org.wpy.test

import org.apache.http.HttpHost
import org.apache.http.client.HttpClient
import org.apache.http.client.config.RequestConfig
import org.apache.http.client.methods.{CloseableHttpResponse, HttpGet}
import org.apache.http.client.protocol.HttpClientContext
import org.apache.http.client.utils.HttpClientUtils
import org.apache.http.conn.ConnectTimeoutException
import org.apache.http.conn.socket.ConnectionSocketFactory
import org.apache.http.conn.ssl.TrustAllStrategy
import org.apache.http.impl.client.{DefaultHttpRequestRetryHandler, HttpClientBuilder, HttpClients}
import org.apache.http.protocol.HttpContext
import org.apache.http.ssl.SSLContexts
import org.apache.http.util.EntityUtils
import org.jsoup.Jsoup

import java.net.{InetSocketAddress, Socket, SocketTimeoutException}
import java.util.logging.{Level, Logger}
import java.util.stream.Collectors
import scala.collection.JavaConverters._
import scala.io.Codec
import scala.reflect.io.File


class SocksConnectionSocketFactory extends ConnectionSocketFactory {
  override def createSocket(context: HttpContext): Socket = {
    val socksaddr = context.getAttribute("socks.addr").asInstanceOf[InetSocketAddress]
    // socket代理
    val proxy = new java.net.Proxy(java.net.Proxy.Type.SOCKS, socksaddr)
    new Socket(proxy);
  }

  override def connectSocket(connectTimeout: Int, sock: Socket, host: HttpHost, remoteAddress: InetSocketAddress, localAddress: InetSocketAddress, context: HttpContext): Socket = {

    val socket = if (null != sock) {
      sock
    } else {
      createSocket(context)
    }
    if (localAddress != null) {
      socket.bind(localAddress)
    }
    try {
      socket.connect(remoteAddress, connectTimeout)
      socket
    } catch {
      case ex: SocketTimeoutException =>
        throw new ConnectTimeoutException(ex, host, remoteAddress.getAddress)
    }
  }
}

object MyHttpClient{
  val log = Logger.getLogger(this.getClass.getName)

  def saveProxyIp(host: String, port: Int, saveFunc: (String, Int) => Unit) = {
    saveFunc(host, port)
  }

  def flushPV[T](url: String,
                 responseOperator: (CloseableHttpResponse, HttpClient) => T,
                 proxySetter: (HttpClientBuilder) => HttpClientBuilder = null,
                 useSocksProxy: Boolean = false) = {
    val clientBuilder = HttpClients.custom()
    clientBuilder.setRetryHandler(new DefaultHttpRequestRetryHandler(2, false))
    clientBuilder.setSSLContext(SSLContexts.custom().loadTrustMaterial(TrustAllStrategy.INSTANCE).build())
    if (proxySetter != null) {
      proxySetter(clientBuilder)
    }

    val param = Map(
      "Connection" -> " keep-alive",
      "Cache-Control" -> " max-age=0",
      "DNT" -> " 1",
      "Upgrade-Insecure-Requests" -> " 1",
      "User-Agent" -> " Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.90 Safari/537.36",
      "Accept" -> " text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9",
      "Accept-Encoding" -> " gzip, deflate",
      "Accept-Language" -> " zh,zh-CN;q=0.9"
    )
    val request = new HttpGet(url)
    request.setConfig(RequestConfig.custom().setCircularRedirectsAllowed(false).build())
    param.foreach { case (k, v) => request.addHeader(k, v) }

    if (useSocksProxy) {
      val context = HttpClientContext.create()
      val socksAddr = InetSocketAddress.createUnresolved("127.0.0.1", 1080)
      context.setAttribute("socks.addr", socksAddr)
      val client = clientBuilder.build()
      val response = client.execute(request, context)
      val res = responseOperator(response, client)
      Thread.sleep(1000)
      HttpClientUtils.closeQuietly(client)
      res
    } else {
      val client = clientBuilder.build()
      val response = client.execute(request)
      val res = responseOperator(response, client)
      Thread.sleep(1000)
      HttpClientUtils.closeQuietly(client)
      res
    }
  }

  def simpleSplit(range: Range, n: Int) = {
    val step = (range.end + (n - 1) * range.step - range.start) / (n * range.step)
    0 until n map { i =>
      val start = range.start + step * i * range.step
      start until math.min(start + step * range.step, range.end)
    }
  }

  def flushWebPage(proxyAddrs: Seq[(String, Int)]) = {
    proxyAddrs.map {
      case (host, port) =>
        try {
          val proxySetters = (clientBuilder: HttpClientBuilder) => {
            clientBuilder.setProxy(new HttpHost(host, port))
          }
          flushPV("http://ap3.news/portal.php?x=891905",
            (response, client) => if (response != null) {
              val body = EntityUtils.toString(response.getEntity, "UTF8")
              val htmlDoc = Jsoup.parse(body)
              val metaTags = htmlDoc.getElementsByTag("meta")
              if (!metaTags.isEmpty) {
                val meta = metaTags.get(0)
                if (meta.attr("http-equiv") == "refresh") {
                  val newUrl = meta.attr("content").splitAt(6)._2
                  val response = client.execute(new HttpGet(newUrl))
                  //                println(EntityUtils.toString(response.getEntity, "UTF8"))
                } else {
                  //                println(body)
                }
              }
            },
            proxySetters)
          (host, port)
        } catch {
          case e: Exception =>
            log.log(Level.SEVERE, "error: " + host + ":" + port + " {" + e.getMessage + "}")
//            e.printStackTrace()
            null
        }
    }
  }

  def readProxyHostsFromFile(file: String) = {
    if (File(file).exists) {
      val reader = File(file).bufferedReader(Codec.UTF8)

      val seq = reader.lines().collect(Collectors.toList[String]()).asScala.map {
        line =>
          val ipPort = line.split(":")
          (ipPort(0), ipPort(1).toInt)
      }
      reader.close()
      seq
    } else {
      Iterator.empty
    }
  }

  def main(args: Array[String]): Unit = {

    //    flushWebPage(readProxyHostsFromFile("ip.txt").toSeq)

    val writer = File("ip.txt").createFile(false).bufferedWriter(append = true, Codec.UTF8)
    simpleSplit(1 until 9, 9).par.foreach { range =>
      range.flatMap { i =>
        flushWebPage(PageParser.getIpPool(PageParser.GaoKeYong._1 + i, PageParser.GaoKeYong._2).toSeq)
      }.filter(_ != null).foreach { case (host, port) =>
        saveProxyIp(host, port, (h, p) => {
          writer.write(h + ":" + p)
          writer.newLine()
          log.info("success: " + host + ":" + port)
        })
      }
      writer.flush()
    }
    writer.close()
  }


}
