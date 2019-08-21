package com.example

import com.example.bean.ChemicalCatalogOrigin
import com.example.repository.ChemicalCatalogOriginRepo
import com.example.util.Kit.postRequest
import org.apache.http.message.BasicNameValuePair
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import java.io.File
import java.lang.Thread.sleep

@RunWith(SpringRunner::class)
@SpringBootTest
class TestMain {

    private val filepath = "E:/test/cas.txt"
    @Autowired
    lateinit var chemicalCatalogOriginRepo: ChemicalCatalogOriginRepo

    @Test
    fun info() {
        val url = "http://www.hgmsds.com/getEhsDetails"
        val text = File(filepath)
        val casList = text.readLines()
        var i = 0
        for (cas in casList) {
            if (cas.isNotEmpty()) {
                print(++i)
                print("\t")
                println(cas)
                getInfo(url, "ghs", cas)
                sleep(5000);
            }
        }

//        val url = "http://www.hgmsds.com/getEhsDetails"
//        getInfo(url, "ghs", "513-88-2")
    }

    fun getInfo(url: String, type: String, cas: String) {
        val params = ArrayList<BasicNameValuePair>()
        params.add(BasicNameValuePair("type", type))
        params.add(BasicNameValuePair("casno", cas))
        val doc = postRequest(url, params)
        println(doc)
        val origin = ChemicalCatalogOrigin(
                cas = cas,
                result = doc
        )
        chemicalCatalogOriginRepo.save(origin)
    }
}