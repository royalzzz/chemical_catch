package com.example.util

import com.gargoylesoftware.htmlunit.BrowserVersion
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController
import com.gargoylesoftware.htmlunit.WebClient
import com.gargoylesoftware.htmlunit.util.Cookie
import java.util.logging.Level

class Browser {
    companion object {

        val webClient: WebClient by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit").level = (Level.OFF)
            java.util.logging.Logger.getLogger("org.apache.commons.httpclient").level = (Level.OFF)
            java.util.logging.Logger.getLogger("com.gargoylesoftware").setLevel(Level.OFF);
            val webClient = WebClient(BrowserVersion.CHROME)
            webClient.options.isThrowExceptionOnFailingStatusCode = false
            webClient.options.isThrowExceptionOnScriptError = false
            webClient.options.printContentOnFailingStatusCode = false
            webClient.options.isActiveXNative = false
            webClient.options.isCssEnabled = true
            webClient.options.isJavaScriptEnabled = true
            webClient.options.screenHeight = 720
            webClient.options.isRedirectEnabled = true
            webClient.javaScriptTimeout = 5000
            webClient.cookieManager.isCookiesEnabled = true
            // webClient.waitForBackgroundJavaScript(5000)
            webClient.addRequestHeader("Host", "www.hgmsds.com")
            webClient.addRequestHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.14; rv:69.0) Gecko/20100101 Firefox/69.0")
            webClient.addRequestHeader("Accept", "application/json, text/javascript, */*; q=0.01")
            webClient.addRequestHeader("Accept-Language", "zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2")
            webClient.addRequestHeader("Accept-Encoding", "gzip, deflate")
            webClient.addRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
            webClient.addRequestHeader("X-Requested-With", "XMLHttpRequest")
            webClient.addRequestHeader("Connection", "keep-alive")
            webClient.addRequestHeader("Referer", "http://www.hgmsds.com/hg-ehs-index?decrypt=QN8gcO%2F1YJh92AXeDQtEkQ%3D%3D")
            val cookies = webClient.cookieManager.cookies
            for (cookie in cookies) {
                webClient.cookieManager.addCookie(cookie)
            }
            webClient.ajaxController = NicelyResynchronizingAjaxController()
            webClient
        }
    }
}