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
        pairList.add(BasicNameValuePair("f", "plist"))
        pairList.add(BasicNameValuePair("type", "word"))
        httpPost.entity = UrlEncodedFormEntity(pairList, Charset.defaultCharset())
        val response = httpClient.execute(httpPost)
        val content = EntityUtils.toString(response.entity, "UTF-8")
        response.close()
        httpClient.close()
        return content
    }
}
