package com.example.controller.crawler

import com.example.bean.Chemicals
import com.example.bean.Supplier
import com.example.repository.ChemicalRepo
import com.example.repository.SupplierRepo
import com.example.util.Kit.sendRequest
import org.jsoup.Jsoup
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.lang.Thread.sleep

@RestController
@RequestMapping("supplier")
class CatchSupplierController {

    val rootUrl: String = "http://cheman.chemnet.com/dict/supplier.cgi"
    val supUrl: String = "http://china.chemnet.com/product/search.cgi"

    @Autowired
    lateinit var supplierRepo: SupplierRepo

    @Autowired
    lateinit var chemicalRepo: ChemicalRepo

    var count = 0
    var percent = 6342

    @RequestMapping("catch")
    fun catchSupplier() {

        val chemicals: List<Chemicals> = chemicalRepo.findAll()
        //3498
        for (i in 6342 until chemicals.size) {
            println("${percent + 1}/${chemicals.size}")
            val cas = chemicals[i].CAS
            val res = sendRequest("$rootUrl?exact=dict&terms=$cas")
            val document = Jsoup.parse(res)
            val div = document.getElementsByClass("hcent_zdl_22")
            for (d in div) {
                val a = d.getElementsByClass("orange xhs")
                if (a.size > 0) {
                    val href = a[0].attr("href")
                    val v = a[0].text().drop(9).dropLast(2)
                    val num = v.toInt()
                    val pages = (num - num % 10) / 10
                    val skey = href.drop(41).dropLast(9)
                    for (j in 0..pages) {
                        cas?.let { getPage(j, skey, it) }
                    }
                }
            }
            percent++
        }
    }

    fun getPage(num: Int, skey: String, cas: String) {
        val href = "$supUrl?skey=$skey;use_cas=0;f=pclist;p=$num"
//        if (count == 90){
//            println("sleep...")
//            sleep(20000)
//            count == 0
//        }
        val res = sendRequest(href)
        val document = Jsoup.parse(res)
        val tables = document.getElementsByAttributeValue("cellpadding", "2")

        var company = ""
        var url = ""
        var phone = ""
        var fax = ""

        for (table in tables) {
            val trs = table.getElementsByTag("tr")
            var i = 0
            for (tr in trs) {
                val tds = tr.getElementsByTag("td")
                if (i == 0) {
                    company = tds[1].text().dropLast(7)
                } else if (i == 1) {
                    phone = tds[1].text()
                } else if (i == 2) {
                    fax = tds[1].text()
                } else if (i == 3) {
                    url = tds[1].text().split(" ")[0]
                } else {
                    i = 0
                    //println("$company,$phone,$fax,$url")
                    val supplier = Supplier()
                    supplier.CAS = cas
                    supplier.company = company
                    supplier.phone = phone
                    supplier.fax = fax
                    supplier.url = url
                    supplierRepo.save(supplier)
                }
                i++
            }
        }
        count++
    }
}