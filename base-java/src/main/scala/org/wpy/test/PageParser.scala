package org.wpy.test

import org.apache.http.util.EntityUtils
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.wpy.test.MyHttpClient.flushPV

import scala.collection.JavaConverters._

object PageParser {

  val KuaiDaiLi = "https://www.kuaidaili.com/free/inha" -> { (doc: Document) => kuaidailiWebPageParse(doc) }
  val Data5u = "http://www.data5u.com/" -> { (doc: Document) => data5uWebPageParse(doc) }
  val GaoKeYong = "http://ip.jiangxianli.com/?page=" -> { (doc: Document) => gaoKeYongWebPageParse(doc) }
  val IP366 = "http://www.ip3366.net/free/?stype=1&page=" -> { (doc: Document) => ip366WebPageParse(doc) }
  val IpHai = "http://www.iphai.com/" -> { (doc: Document) => ipHaiWebPageParse(doc) }
  val MyProxy = "https://www.my-proxy.com/free-proxy-list.html" -> { (doc: Document) => myProxyWebPageParse(doc) }
  val ProxyListPlus = "https://list.proxylistplus.com/Fresh-HTTP-Proxy-List-" -> { (doc: Document) => proxyListPlusWebPageParse(doc) }
  val QuanWangWeb = "http://www.goubanjia.com/" -> { (doc: Document) => quanWangWebPageParse(doc) }
  val Xicidaili = "https://www.xicidaili.com/nn" -> { (doc: Document) => xicidailiWebPageParse(doc) }
  val IP66 = "http://www.66ip.cn/" -> { (doc: Document) => ip66WebPageParse(doc) }
  val Y7IP = "https://www.7yip.cn/free/?action=china&page=" -> { (doc: Document) => y7IPWebPageParse(doc) }
  val Xiadaili = "http://www.xiladaili.com/http/"-> { (doc: Document) => xiadailiWebPageParse(doc) }


  def getIpPool(url: String, parsePage: (Document) => Iterator[(String, Int)]) = {
    flushPV(url, (response, client) =>
      if (response != null) {
        parsePage(Jsoup.parseBodyFragment(EntityUtils.toString(response.getEntity, "UTF8")))
      } else {
        null
      })
  }

  def xiadailiWebPageParse(htmlDoc: Document) = {
    val elements = htmlDoc.getElementsByTag("tr")
    if (elements.isEmpty) {
      Iterator.empty
    } else {
      elements.remove(0)
      elements.iterator().asScala.filter(_ != null).map { e =>
        val ipPort = e.child(0).text().split(":")
        val host = ipPort(0)
        val port = ipPort(1).toInt
        (host, port)
      }
    }
  }

  def y7IPWebPageParse(htmlDoc: Document) = {
    val elements = htmlDoc.getElementsByTag("tr")
    if (elements.isEmpty) {
      Iterator.empty
    } else {
      elements.remove(0)
      elements.iterator().asScala.filter(_ != null).map { e =>
        val host = e.child(0).text()
        val port = e.child(1).text().toInt
        (host, port)
      }
    }
  }

  /**
   * http://www.66ip.cn/2.html
   */
  def ip66WebPageParse(htmlDoc: Document) = {
    val elements = htmlDoc.getElementsByTag("tr")
    if (elements.size() > 3) {
      elements.remove(0)
      elements.remove(0)
      elements.remove(0)
      elements.iterator().asScala.filter(_ != null).map { e =>
        val host = e.child(0).text()
        val port = e.child(1).text().toInt
        (host, port)
      }
    } else {
      Iterator.empty
    }
  }

  /**
   * https://www.kuaidaili.com/free/inha
   */
  private def kuaidailiWebPageParse(htmlDoc: Document) = {
    val elements = htmlDoc.getElementsByTag("tr")
    elements.remove(0)
    elements.iterator().asScala.filter(_ != null).map { e =>
      val host = e.child(0).text()
      val port = e.child(1).text().toInt
      (host, port)
    }
  }

  /**
   * http://www.data5u.com/
   */
  private def data5uWebPageParse(htmlDoc: Document) = {
    val elements = htmlDoc.getElementsByClass("l2")
    elements.iterator().asScala.map { e =>
      val host = e.child(0).text()
      val port = e.child(1).text().toInt
      (host, port)
    }
  }

  /**
   * http://ip.jiangxianli.com/?page=1
   */
  private def gaoKeYongWebPageParse(htmlDoc: Document) = {
    val elements = htmlDoc.getElementsByTag("tr")
    if (elements == null || elements.isEmpty) {
      Iterator.empty
    } else {
      elements.remove(0)
      elements.iterator().asScala.map { e =>
        val host = e.child(0).text()
        val port = e.child(1).text().toInt
        (host, port)
      }
    }
  }

  /**
   * http://www.ip3366.net/?stype=1&page=1
   */
  private def ip366WebPageParse(htmlDoc: Document) = {
    val elements = htmlDoc.getElementsByTag("tr")
    elements.remove(0)
    elements.iterator().asScala.map { e =>
      val host = e.child(0).text()
      val port = e.child(1).text().toInt
      (host, port)
    }
  }

  /**
   * http://www.iphai.com/
   */
  private def ipHaiWebPageParse(htmlDoc: Document) = {
    val elements = htmlDoc.getElementsByTag("tr")
    elements.remove(0)
    elements.iterator().asScala.map { e =>
      val host = e.child(0).text()
      val port = e.child(1).text().toInt
      (host, port)
    }
  }

  /**
   * https://www.my-proxy.com/free-proxy-list.html
   */
  private def myProxyWebPageParse(htmlDoc: Document) = {
    val elements = htmlDoc.getElementsByClass("list").html().split("<br>")
    elements.map { e =>
      val ipPort = e.split("#")(0).split(":")
      val host = ipPort(0)
      val port = ipPort(1).toInt
      (host, port)
    }
  }

  /**
   * https://list.proxylistplus.com/Fresh-HTTP-Proxy-List-1
   */
  private def proxyListPlusWebPageParse(htmlDoc: Document) = {
    val elements = htmlDoc.getElementsByTag("tr")
    elements.iterator().asScala.filter(e => e.attr("class") == "cells" && e.attr("onmouseover") == "this.className='cells2'").map { e =>
      try {
        val host = e.child(1).text()
        val port = e.child(2).text().toInt
        (host, port)
      } catch {
        case e: Exception =>
          e.printStackTrace()
          null
      }
    }.filter(_ != null)
  }

  /**
   * http://www.goubanjia.com/
   */
  private def quanWangWebPageParse(htmlDoc: Document) = {
    val elements = htmlDoc.getElementsByTag("tr")
    elements.remove(0)
    elements.iterator().asScala.map { e =>
      val child = e.child(0)
      child.select("p").remove()
      val ipPort = child.text().replaceAll("\\s+", "").replaceAll("\n", "").split(":")
      val host = ipPort(0)
      val port = ipPort(1).toInt
      (host, port)
    }
  }

  /**
   * https://www.xicidaili.com/nn
   */
  private def xicidailiWebPageParse(htmlDoc: Document) = {
    val elements = htmlDoc.getElementsByTag("tr")
    elements.remove(0)
    elements.iterator().asScala.map { e =>
      val host = e.child(1).text()
      val port = e.child(2).text().toInt
      (host, port)
    }
  }

  def main(args: Array[String]): Unit = {
    1 until 2299 foreach { i =>
      PageParser.getIpPool(PageParser.Xiadaili._1 + i+"/", PageParser.Xiadaili._2).toSeq
    }
  }
}
