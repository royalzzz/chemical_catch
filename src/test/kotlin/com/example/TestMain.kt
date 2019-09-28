package com.example

import com.example.bean.ChemicalCatalogOrigin
import com.example.repository.ChemicalCatalogOriginRepo
import com.example.util.Browser.Companion.webClient
import com.example.util.Escape
import com.example.util.Kit.postContent
import com.example.util.Kit.postRequest
import com.example.util.Kit.transform
import com.example.util.WebUtil
import com.example.util.WebUtil.request
import com.gargoylesoftware.htmlunit.html.*
import com.gargoylesoftware.htmlunit.javascript.host.html.HTMLButtonElement
import com.gargoylesoftware.htmlunit.javascript.host.html.HTMLInputElement
import com.gargoylesoftware.htmlunit.util.Cookie
import net.minidev.json.JSONArray
import net.minidev.json.JSONObject
import net.minidev.json.parser.JSONParser
import org.apache.commons.logging.LogFactory
import org.apache.http.message.BasicNameValuePair
import org.apache.tomcat.jni.Proc.wait
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import java.io.File
import java.lang.Thread.sleep
import java.util.*
import java.util.logging.Level
import java.util.logging.LogManager
import kotlin.collections.ArrayList

//@RunWith(SpringRunner::class)
//@SpringBootTest
class TestMain {

    //private val filepath = "C:\\Users\\Administrator\\IdeaProjects\\chemical_catch\\src\\test\\resources\\cas"
    //private val filepath = "E:\\code\\Java\\catch\\src\\test\\resources\\cas"

    // 17853678882
    // 15615125129
    // val filePath = "/Users/royal/IdeaProjects/chemical_catch/src/test/resources/cas3000"
    // val rootPath = "/Users/royal/Downloads/cas/"
    val filePath = "E:\\code\\Java\\catch\\src\\test\\resources\\cas3000"
    val rootPath = "E:\\库\\桌面\\cas\\"
    val proxyIP = "47.105.127.2"
    val proxyPort = 16818
    val username = "15615125129"
    val password = "Iphone521"

    @Test
    fun info() {
        // 15615125129
        val cookieStr = login()
        if (cookieStr.isNotEmpty()) {
            //A2034F0CC328E9DF15FDC5EE8BDE4E10
            val sessionId = cookieStr.split(";")[0]
            val jsessionId = sessionId.split("=")[1]
            println(jsessionId)
            start(jsessionId)
        }
    }

    fun login(): String {
        val pairList = ArrayList<BasicNameValuePair>()
        pairList.add(BasicNameValuePair("username", username))
        pairList.add(BasicNameValuePair("password", password))
        val response = postRequest("http://www.hgmsds.com/hgLoginCheck", pairList, proxyIP, proxyPort)
        response.allHeaders.forEach {
            if (it.name == "Set-Cookie") {
                return it.value
            }
        }
        return ""
    }

    fun start(jsessionId: String) {
        val cookie1 = Cookie(".hgmsds.com", "Hm_lpvt_581f7711e663e3f8f681a6c26d63b804", (Date().time / 1000).toString())
        val cookie2 = Cookie(".hgmsds.com", "Hm_lpvt_f27a00454fe3332070be8b71a0c64602", (Date().time / 1000).toString())
        val cookie3 = Cookie(".hgmsds.com", "Hm_lvt_581f7711e663e3f8f681a6c26d63b804", "1600946548087|1567491001,1569410532")
        val cookie4 = Cookie(".hgmsds.com", "Hm_lvt_f27a00454fe3332070be8b71a0c64602", "1600946547954|1567491001,1569410532")
        val cookie5 = Cookie("www.hgmsds.com", "JSESSIONID", jsessionId)

        val cookie6 = Cookie(".hgmsds.com", "Qs_lvt_56176", (Date().time / 1000).toString())
        val cookie7 = Cookie(".hgmsds.com", "Qs_pv_56176", "961877667308466800%2C1622712727564054300%2C2229648327691822300%2C1815303298012249900%2C3740016893519566000")
        webClient.cookieManager.addCookie(cookie1)
        webClient.cookieManager.addCookie(cookie2)
        webClient.cookieManager.addCookie(cookie3)
        webClient.cookieManager.addCookie(cookie4)
        webClient.cookieManager.addCookie(cookie5)
        webClient.cookieManager.addCookie(cookie6)
        webClient.cookieManager.addCookie(cookie7)
        webClient.options.proxyConfig.proxyHost = proxyIP
        webClient.options.proxyConfig.proxyPort = proxyPort
        webClient.waitForBackgroundJavaScript(5000)
        val cass = File(filePath).readLines()
        var i = 1
        for (cas in cass) {
            if (cas.isNotEmpty()) {
                getInfoByCas(cas)
                println("hello, now progress is $i / ${cass.size}, $cas")
                i++
                sleep(10000)
                println("wait for 10s...")
            }
        }
    }

    fun getInfoByCas(cas: String) {
        val pairList = ArrayList<BasicNameValuePair>()
        pairList.add(BasicNameValuePair("inputValue", cas))
        val list = postContent("http://www.hgmsds.com/showChemicalDetails", pairList, proxyIP, proxyPort)
        val jsonList = org.json.JSONArray(list)
        if (jsonList.length() > 0) {
            val json = jsonList[0] as org.json.JSONObject
            val html = request("http://www.hgmsds.com/hg-ehs-index?decrypt=${json["id"]}")
            File("$rootPath$cas").mkdirs()
            File("$rootPath$cas/cd.html").writeText(html.asXml())
            val gbtns = html.getElementsByTagName("a")
            for (g in gbtns) {
                when (g.getAttribute("onclick")) {
                    "menuChange(this,'ghs')" -> getGHSPage(cas, g)
                    "menuChange(this,'dan')" -> getDANPage(cas, g)
                    "menuChange(this,'gbz')" -> getGBZPage(cas, g)
                    "menuChange(this,'guide')" -> getGUIDEPage(cas, g)
                    "menuChange(this,'ccs')" -> getCCSPage(cas, g)
                    "menuChange(this,'ics')" -> getICSPage(cas, g)
                }
            }
        }
    }

    // 危险性分类
    fun getGHSPage(cas: String, page: DomElement) {
        val btn = page as HtmlAnchor
        val ghs = btn.click<HtmlPage>()
        sleep(5000)
        File("$rootPath$cas/ghs.html").writeText(ghs.asXml())
    }

    // 危化品目录
    fun getDANPage(cas: String, page: DomElement) {
        val btn = page as HtmlAnchor
        val ghs = btn.click<HtmlPage>()
        sleep(5000)
        File("$rootPath$cas/dan.html").writeText(ghs.asXml())
    }

    // 职业接触限制
    fun getGBZPage(cas: String, page: DomElement) {
        val btn = page as HtmlAnchor
        val ghs = btn.click<HtmlPage>()
        sleep(5000)
        File("$rootPath$cas/gbz.html").writeText(ghs.asXml())
    }

    // 安全防护指南
    fun getGUIDEPage(cas: String, page: DomElement) {
        val btn = page as HtmlAnchor
        val ghs = btn.click<HtmlPage>()
        sleep(5000)
        File("$rootPath$cas/guide.html").writeText(ghs.asXml())
    }

    // 中国监管目录
    fun getCCSPage(cas: String, page: DomElement) {
        val btn = page as HtmlAnchor
        val ghs = btn.click<HtmlPage>()
        sleep(5000)
        File("$rootPath$cas/ccs.html").writeText(ghs.asXml())
    }

    // 现有物质目录
    fun getICSPage(cas: String, page: DomElement) {
        val btn = page as HtmlAnchor
        val ghs = btn.click<HtmlPage>()
        sleep(5000)
        File("$rootPath$cas/ics.html").writeText(ghs.asXml())
    }
}