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
        val proxy = HttpHost("47.104.3.65", 16816, "http")
        val requestConfig = RequestConfig.custom().setProxy(proxy).build()
        val httpPost = HttpPost(url)
//        pairList.add(BasicNameValuePair("f", "plist"))
//        pairList.add(BasicNameValuePair("type", "word"))
        httpPost.addHeader("Cookie", "Qs_lvt_56176=1565671758%2C1566012478%2C1566609635; Qs_pv_56176=1327484612874053600%2C2828586052325585000%2C938478417055299000%2C3368989890354996000%2C3182233178277208000; Hm_lvt_f27a00454fe3332070be8b71a0c64602=1566031798,1566609635,1566611561,1566627608; Hm_lvt_581f7711e663e3f8f681a6c26d63b804=1566031798,1566609635,1566611561,1566627610; SESSION_LOGIN_USERNAME=17853678882; SESSION_LOGIN_PASSWORD=cd265e88a1d7aa9141f55560cf879b45; JSESSIONID=26D71B83CFFFE260954F9E9C1243B6CB; mediav=%7B%22eid%22%3A%22105583%22%2C%22ep%22%3A%22%22%2C%22vid%22%3A%22%25%24)1UNa*pJ%3Ai%5D5Y%3C7DM5%22%2C%22ctn%22%3A%22%22%7D; Hm_lpvt_f27a00454fe3332070be8b71a0c64602=1566629573; Hm_lpvt_581f7711e663e3f8f681a6c26d63b804=1566629573")
        httpPost.entity = UrlEncodedFormEntity(pairList, Charset.defaultCharset())
        httpPost.config = requestConfig
        val response = httpClient.execute(httpPost)
        val content = EntityUtils.toString(response.entity, "UTF-8")
        response.close()
        httpClient.close()
        return content
    }
}
