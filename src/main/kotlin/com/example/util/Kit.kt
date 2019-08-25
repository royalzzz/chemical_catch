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
import org.apache.http.client.methods.HttpUriRequest

object Kit {

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
        val proxy = HttpHost("47.104.223.189", 16816, "http")
        val requestConfig = RequestConfig.custom().setProxy(proxy).build()
        val httpPost = HttpPost(url)
//        pairList.add(BasicNameValuePair("f", "plist"))
//        pairList.add(BasicNameValuePair("type", "word"))
        httpPost.addHeader("Cookie", "JSESSIONID=FC68F3795B7744F58AEE17C95B3BD241; Qs_lvt_56176=1566701705; Qs_pv_56176=1947671252255700700%2C754914658825798100%2C3628302513574993000%2C764950073246528500%2C1380783026688815600; Hm_lvt_f27a00454fe3332070be8b71a0c64602=1566701705; Hm_lpvt_f27a00454fe3332070be8b71a0c64602=1566708987; Hm_lvt_581f7711e663e3f8f681a6c26d63b804=1566701705; Hm_lpvt_581f7711e663e3f8f681a6c26d63b804=1566708987; mediav=%7B%22eid%22%3A%22105583%22%2C%22ep%22%3A%22%22%2C%22vid%22%3A%22F%23a%3AZ%25fL3%25%3Ai%5D5Yq%3Ffr%3A%22%2C%22ctn%22%3A%22%22%7D")
        httpPost.entity = UrlEncodedFormEntity(pairList, Charset.defaultCharset())
        httpPost.config = requestConfig
        val response = httpClient.execute(httpPost)
        val content = EntityUtils.toString(response.entity, "UTF-8")
        response.close()
        httpClient.close()
        return content
    }
}
