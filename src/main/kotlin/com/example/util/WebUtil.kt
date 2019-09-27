package com.example.util

import com.example.util.Browser.Companion.webClient
import com.gargoylesoftware.htmlunit.html.HtmlPage

object WebUtil {

    fun request(url: String): HtmlPage {
        var page: HtmlPage? = null
        try {
            page = webClient.getPage(url)
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            //webClient.close()
        }
        return page!!
//        val pageXml = page!!.asXml() ?: return Jsoup.parse("")
//        return Jsoup.parse(pageXml)
    }

    fun get(url: String): HtmlPage? {
        var page: HtmlPage? = null
        try {
            page = webClient.getPage(url)
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            webClient.close()
        }
        webClient.waitForBackgroundJavaScript(5000)
        return page
    }
}