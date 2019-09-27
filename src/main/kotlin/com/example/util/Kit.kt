package com.example.util

import org.apache.http.Header
import org.apache.http.client.entity.UrlEncodedFormEntity
import org.apache.http.client.methods.HttpGet
import org.apache.http.client.methods.HttpPost
import org.apache.http.impl.client.HttpClients
import org.apache.http.message.BasicNameValuePair
import org.apache.http.util.EntityUtils
import org.omg.CORBA.NameValuePair
import java.nio.charset.Charset
import org.apache.http.impl.client.CloseableHttpClient
import org.apache.http.HttpHost
import org.apache.http.client.config.RequestConfig
import org.apache.http.client.methods.HttpUriRequest

object Kit {

    fun transform(s: String): String {
        return s.replace("%20", "+")
                .replace("%2F", "/")
                .replace("%3F", "?")
                .replace("%25", "%")
                .replace("%23", "#")
                .replace("%26", "&")
                .replace("%3D", "=")
    }

    @Throws(Exception::class)
    fun sendRequest(url: String): String {
        val httpClient = HttpClients.createDefault()
        val httpGet = HttpGet(url)
        var content = ""
        try {
            val response = httpClient.execute(httpGet as HttpUriRequest?)
            content = EntityUtils.toString(response.entity, "UTF-8")
            response.close()
            httpClient.close()
        } catch (exp: Exception) {

        }

        return content
    }

    @Throws(Exception::class)
    fun postRequest(url: String, pairList: ArrayList<BasicNameValuePair>): String {
        val httpClient = HttpClients.createDefault()
        val proxy = HttpHost("47.105.75.155", 16816, "http")
        val requestConfig = RequestConfig.custom().setProxy(proxy).build()
        val httpPost = HttpPost(url)
//        pairList.add(BasicNameValuePair("f", "plist"))
//        pairList.add(BasicNameValuePair("type", "word"))
//        httpPost.addHeader("Cookie", "Qs_lvt_56176=1566448872%2C1566542838%2C1566785621%2C1566867925%2C1566954454; Qs_pv_56176=1142881519804044900%2C392688692186695550%2C3449993652124668400%2C3748071990862829600%2C2612339575116150300; Hm_lvt_f27a00454fe3332070be8b71a0c64602=1566448873,1566785622,1566867926,1566954454; Hm_lvt_581f7711e663e3f8f681a6c26d63b804=1566448873,1566785622,1566867926,1566954454; SESSION_LOGIN_USERNAME=15615125129; SESSION_LOGIN_PASSWORD=cd265e88a1d7aa9141f55560cf879b45; JSESSIONID=02C74DD7243280C477D441BD25D79968; Hm_lpvt_581f7711e663e3f8f681a6c26d63b804=1566954488; Hm_lpvt_f27a00454fe3332070be8b71a0c64602=1566954488; mediav=%7B%22eid%22%3A%22105583%22%2C%22ep%22%3A%22%22%2C%22vid%22%3A%22LMRMfqzlwr%3A_GHNr%60pJd%22%2C%22ctn%22%3A%22%22%7D")
        httpPost.addHeader("Host", "www.hgmsds.com")
        httpPost.addHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.14; rv:69.0) Gecko/20100101 Firefox/69.0")
        httpPost.addHeader("Accept", "application/json, text/javascript, */*; q=0.01")
        httpPost.addHeader("Accept-Language", "zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2")
        httpPost.addHeader("Accept-Encoding", "gzip, deflate")
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
        httpPost.addHeader("X-Requested-With", "XMLHttpRequest")
        httpPost.addHeader("Connection", "keep-alive")
        httpPost.addHeader("Referer", "http://www.hgmsds.com/hg-ehs-index?decrypt=QN8gcO%2F1YJh92AXeDQtEkQ%3D%3D")
        //httpPost.addHeader("Cookie", "JSESSIONID=39C074A61417066661FD26BEE2D55394")

        httpPost.entity = UrlEncodedFormEntity(pairList, Charset.defaultCharset())
        httpPost.config = requestConfig
        val response = httpClient.execute(httpPost)
        val content = EntityUtils.toString(response.entity, "UTF-8")
        response.close()
        httpClient.close()
        return content
    }
}
