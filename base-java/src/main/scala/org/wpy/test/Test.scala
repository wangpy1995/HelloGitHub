package org.wpy.test

import org.apache.http.HttpHost
import org.apache.http.config.RegistryBuilder
import org.apache.http.conn.socket.ConnectionSocketFactory
import org.apache.http.impl.client.HttpClientBuilder
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager
import org.apache.http.util.EntityUtils

object Test {
  def main(args: Array[String]): Unit = {
    //    val proxySetter = (clientBuilder: HttpClientBuilder) => {
    //      val reg = RegistryBuilder.create[ConnectionSocketFactory]()
    //        .register("http", new SocksConnectionSocketFactory()).build()
    //      val connManager = new PoolingHttpClientConnectionManager(reg)
    //      clientBuilder.setConnectionManager(connManager)
    //    }
    val proxySetter = (clientBuilder: HttpClientBuilder) => {
      clientBuilder.setProxy(new HttpHost("114.215.27.235", 80))
    }

    try {
      MyHttpClient.flushPV("http://bb8.news/portal.php?x=890796",
        (response, _) =>
          println(EntityUtils.toString(response.getEntity, "utf8")),
        proxySetter)
    } catch {
      case e: Exception => println(e.getMessage)
    }

  }
}
