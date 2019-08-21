package com.example.util

import org.apache.http.client.entity.UrlEncodedFormEntity
import org.apache.http.client.methods.HttpGet
import org.apache.http.client.methods.HttpPost
import org.apache.http.impl.client.HttpClients
import org.apache.http.message.BasicNameValuePair
import org.apache.http.util.EntityUtils
import org.omg.CORBA.NameValuePair
import java.nio.charset.Charset

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
        val httpPost = HttpPost(url)
//        pairList.add(BasicNameValuePair("f", "plist"))
//        pairList.add(BasicNameValuePair("type", "word"))
        httpPost.addHeader("Cookie", "Qs_lvt_56176=1565671758%2C1566012478; Qs_pv_56176=4241873840319474700%2C4333185141342658000%2C2105487237755936000%2C1992015309950017800%2C4148939872735331000; Hm_lvt_f27a00454fe3332070be8b71a0c64602=1566029060,1566029165,1566030580,1566031798; Hm_lvt_581f7711e663e3f8f681a6c26d63b804=1566029060,1566029164,1566030581,1566031798; SESSION_LOGIN_USERNAME=15610508995; SESSION_LOGIN_PASSWORD=cd265e88a1d7aa9141f55560cf879b45; JSESSIONID=39A172A61749AD45FD6A214BB9BBF7E8; mediav=%7B%22eid%22%3A%22105583%22%2C%22ep%22%3A%22%22%2C%22vid%22%3A%22%25%24)1UNa*pJ%3Agv)IV%24q%3A%24%22%2C%22ctn%22%3A%22%22%7D; Hm_lpvt_581f7711e663e3f8f681a6c26d63b804=1566032492; Hm_lpvt_f27a00454fe3332070be8b71a0c64602=1566032491")
        httpPost.entity = UrlEncodedFormEntity(pairList, Charset.defaultCharset())
        val response = httpClient.execute(httpPost)
        val content = EntityUtils.toString(response.entity, "UTF-8")
        response.close()
        httpClient.close()
        return content
    }
}
