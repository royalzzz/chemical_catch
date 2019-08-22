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

    private val filepath = "/Users/royal/IdeaProjects/chemical_catch/src/test/resources/cas"
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
        //JSESSIONID=71EC9A261F66F01859BDDBDA29C5F8E6; Qs_lvt_56176=1566448872; Qs_pv_56176=1962859087683456000%2C3067919151266341000%2C4218948666408735000%2C4398872970285031400%2C4381712557310873600; mediav=%7B%22eid%22%3A%22105583%22%2C%22ep%22%3A%22%22%2C%22vid%22%3A%22LMRMfqzlwr%3Ahi%5BQkC%23uv%22%2C%22ctn%22%3A%22%22%7D; Hm_lvt_f27a00454fe3332070be8b71a0c64602=1566448873; Hm_lpvt_f27a00454fe3332070be8b71a0c64602=1566456088; Hm_lvt_581f7711e663e3f8f681a6c26d63b804=1566448873; Hm_lpvt_581f7711e663e3f8f681a6c26d63b804=1566456089
        //JSESSIONID=71EC9A261F66F01859BDDBDA29C5F8E6; Qs_lvt_56176=1566448872; Qs_pv_56176=3067919151266341000%2C4218948666408735000%2C4398872970285031400%2C4381712557310873600%2C2202059588098944300; mediav=%7B%22eid%22%3A%22105583%22%2C%22ep%22%3A%22%22%2C%22vid%22%3A%22LMRMfqzlwr%3Ahi%5BQkC%23uv%22%2C%22ctn%22%3A%22%22%7D; Hm_lvt_f27a00454fe3332070be8b71a0c64602=1566448873; Hm_lpvt_f27a00454fe3332070be8b71a0c64602=1566456192; Hm_lvt_581f7711e663e3f8f681a6c26d63b804=1566448873; Hm_lpvt_581f7711e663e3f8f681a6c26d63b804=1566456192
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