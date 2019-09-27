package com.example

import com.example.bean.ChemicalCatalogOrigin
import com.example.repository.ChemicalCatalogOriginRepo
import com.example.util.Browser.Companion.webClient
import com.example.util.Escape
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
    val filePath = "/Users/royal/IdeaProjects/chemical_catch/src/test/resources/cas3000"
    val rootPath = "/Users/royal/Downloads/cas/"

    @Test
    fun info() {
        // 15615125129
        start()
    }

    fun start() {
        val cookie1 = Cookie(".hgmsds.com", "Hm_lpvt_581f7711e663e3f8f681a6c26d63b804", "1569554233")
        val cookie2 = Cookie(".hgmsds.com", "Hm_lpvt_f27a00454fe3332070be8b71a0c64602", "1569554233")
        val cookie3 = Cookie(".hgmsds.com", "Hm_lvt_581f7711e663e3f8f681a6c26d63b804", "1569479662,1569553128")
        val cookie4 = Cookie(".hgmsds.com", "Hm_lvt_f27a00454fe3332070be8b71a0c64602", "1569479662,1569553129")
        val cookie5 = Cookie("www.hgmsds.com", "JSESSIONID", "B1E2A251DA2CAD6C3BD9D64B5D251F6B")
        webClient.cookieManager.addCookie(cookie1)
        webClient.cookieManager.addCookie(cookie2)
        webClient.cookieManager.addCookie(cookie3)
        webClient.cookieManager.addCookie(cookie4)
        webClient.cookieManager.addCookie(cookie5)
        webClient.waitForBackgroundJavaScript(5000)
        val cass = File(filePath).readLines()
        var i = 1
        for (cas in cass) {
            if (cas.isNotEmpty()) {
                getInfoByCas(cas)
                println("hello, now progress is $i / ${cass.size}, $cas")
                i++
            }
        }

    }

    fun getInfoByCas(cas: String) {
        val pairList = ArrayList<BasicNameValuePair>()
        pairList.add(BasicNameValuePair("inputValue", cas))
        val list = postRequest("http://www.hgmsds.com/showChemicalDetails", pairList)
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