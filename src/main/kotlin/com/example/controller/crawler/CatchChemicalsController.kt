package com.example.controller.crawler

import com.example.repository.ChemicalRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("chemicals")
class CatchChemicalsController {

    @Autowired
    lateinit var chemicalRepo: ChemicalRepo

    private val rootUrl = "https://www.chemicalbook.com/"

    @RequestMapping("catch")
    @Throws(Exception::class)
    fun chemicals() {
        println(chemicalRepo.findAll().size)
//        val chemicals = ArrayList<Chemicals>()
//        for (i in 0..323) {
//            val uri = "CASDetailList_" + i * 100 + ".htm"
//            val res = sendRequest(rootUrl + uri)
//            val document = Jsoup.parse(res)
//            val table = document.getElementById("ContentPlaceHolder1_ProductClassDetail")
//            val tbody = table.getElementsByTag("tbody")
//            for (body in tbody) {
//                val trs = body.getElementsByTag("tr")
//                for (tr in trs) {
//                    val tds = tr.getElementsByTag("td")
//                    var m = 0
//                    val chemical = Chemicals()
//                    for (td in tds) {
//                        if (m == 0) {
//                            chemical.CAS = td.text()
//                        } else if (m == 1) {
//                            chemical.nameCn = td.text()
//                        } else if (m == 2) {
//                            chemical.nameEn = td.text()
//                        } else if (m == 3) {
//                            chemical.MF = td.text()
//                        }
//                        m++
//                    }
//                    if (chemical.CAS != null) {
//                        chemicalService!!.insert(chemical)
//                    }
//                }
//            }
//            println("已完成 " + (i + 1))
//        }
    }
}
