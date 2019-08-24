package com.example.util

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







object Kit {

    @Throws(Exception::class)
    fun sendRequest(url: String): String {
        val httpClient = HttpClients.createDefault()
        val httpGet = HttpGet(url)
        var content = ""
        try {
            val response = httpClient.execute(httpGet)
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
        val proxy = HttpHost("112.74.114.105", 16816, "http")
        val requestConfig = RequestConfig.custom().setProxy(proxy).build()
        val httpPost = HttpPost(url)
//        pairList.add(BasicNameValuePair("f", "plist"))
//        pairList.add(BasicNameValuePair("type", "word"))
        httpPost.addHeader("Cookie", "Qs_lvt_56176=1565671758%2C1566012478%2C1566609635; Qs_pv_56176=285407530238349000%2C4456563720449832400%2C1523764281054921000%2C2511363504612290600%2C1202200470595164700; Hm_lvt_f27a00454fe3332070be8b71a0c64602=1566030580,1566031798,1566609635,1566611561; Hm_lvt_581f7711e663e3f8f681a6c26d63b804=1566030581,1566031798,1566609635,1566611561; SESSION_LOGIN_USERNAME=17853678882; SESSION_LOGIN_PASSWORD=cd265e88a1d7aa9141f55560cf879b45; JSESSIONID=59B497F7EC9A7FFCDB17D9F463FB38C1; mediav=%7B%22eid%22%3A%22105583%22%2C%22ep%22%3A%22%22%2C%22vid%22%3A%22%25%24)1UNa*pJ%3Ai%5D5Y%3C7DM5%22%2C%22ctn%22%3A%22%22%7D; Hm_lpvt_f27a00454fe3332070be8b71a0c64602=1566612061; Hm_lpvt_581f7711e663e3f8f681a6c26d63b804=1566612062")
        httpPost.entity = UrlEncodedFormEntity(pairList, Charset.defaultCharset())
        httpPost.config = requestConfig
        val response = httpClient.execute(httpPost)
        val content = EntityUtils.toString(response.entity, "UTF-8")
        response.close()
        httpClient.close()
        return content
    }
}
