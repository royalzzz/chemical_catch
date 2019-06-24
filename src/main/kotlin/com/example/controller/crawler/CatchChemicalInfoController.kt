package com.example.controller.crawler

import com.example.bean.Stream
import com.example.repository.ChemicalRepo
import com.example.repository.StreamRepo
import org.jsoup.Jsoup
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import com.example.util.Kit.sendRequest
import org.springframework.beans.factory.annotation.Autowired
import java.lang.Thread.sleep

@RestController
@RequestMapping("info")
class CatchChemicalInfoController {

    private val rootUrl = "http://cheman.chemnet.com/dict/supplier.cgi?exact=dict&terms="

    @Autowired
    lateinit var streamRepo: StreamRepo

    @Autowired
    lateinit var chemicalRepo: ChemicalRepo

    @RequestMapping("catch")
    @Throws(Exception::class)
    fun info() {
        val chemicalsList = chemicalRepo.findAll()
        var i: Int = 0
        for (i in 0 until chemicalsList.size) {
            chemicalsList.get(i).CAS?.let { getInfoByCAS(it) }
            println("进度： " + i + " / " + chemicalsList.size)
            if (i % 100 == 0) {
                println("歇一会，就20秒...")
                sleep(20000)
            }
        }
    }

    @RequestMapping("fix")
    @Throws(Exception::class)
    fun fix() {
//        val chemicalsList = chemicalInfoService!!.selectBrokenRows()
//        var i: Int
//        i = 1
//        while (i <= chemicalsList.size) {
//            fixInfoByCAS(chemicalsList[i - 1])
//            println("进度： " + i + " / " + chemicalsList.size)
//            if (i % 100 == 0) {
//                println("歇一会，就10秒...")
//                sleep(20000)
//            }
//            i++
//        }
    }

    @Throws(Exception::class)
    fun fixInfoByCAS() {
//        val res = sendRequest(rootUrl + chemical.cas)
//        val document = Jsoup.parse(res)
//        val tables = document.getElementsByAttributeValue("cellpadding", "7")
//        var aliasCn = ""
//        var aliasEn = ""
//        var mole = ""
//        var density = ""
//        var melt = ""
//        var boil = ""
//        var falsh = ""
//        var solub = ""
//        var tension = ""
//        var dangerMark = ""
//        var riskCode = ""
//        var safeCode = ""
//
//        for (table in tables) {
//
//            val tds = table.getElementsByTag("td")
//            for (i in tds.indices) {
//
//                if (tds[i].text() == "中文别名：") {
//                    aliasCn = tds[i + 1].text()
//                }
//                if (tds[i].text() == "英文别名：") {
//                    aliasEn = tds[i + 1].text()
//                }
//                if (tds[i].text() == "分子量：") {
//                    mole = tds[i + 1].text()
//                }
//                if (tds[i].text() == "密度：") {
//                    density = tds[i + 1].text()
//                }
//                if (tds[i].text() == "熔点：") {
//                    melt = tds[i + 1].text()
//                }
//                if (tds[i].text() == "沸点：") {
//                    boil = tds[i + 1].text()
//                }
//                if (tds[i].text() == "闪点：") {
//                    falsh = tds[i + 1].text()
//                }
//                if (tds[i].text() == "水溶性：") {
//                    solub = tds[i + 1].text()
//                }
//                if (tds[i].text() == "蒸汽压：") {
//                    tension = tds[i + 1].text()
//                }
//                if (tds[i].text() == "危险性标志:") {
//                    dangerMark = tds[i + 1].text()
//                }
//                if (tds[i].text() == "风险术语：") {
//                    riskCode = tds[i + 1].text()
//                }
//                if (tds[i].text() == "安全术语：") {
//                    safeCode = tds[i + 1].text()
//                }
//            }
//        }
//        chemical.aliasCn = aliasCn
//        chemical.aliasEn = aliasEn
//        chemical.molecularWeight = mole
//        chemical.density = density
//        chemical.meltingPoint = melt
//        chemical.boilingPoint = boil
//        chemical.flashPoint = falsh
//        chemical.waterSolubility = solub
//        chemical.tension = tension
//        chemical.dangerMark = dangerMark
//        chemical.riskCode = riskCode
//        chemical.safeCode = safeCode
//        chemicalInfoService!!.updateByCAS(chemical)
    }

    @Throws(Exception::class)
    fun getInfoByCAS(cas: String) {
        val res = sendRequest(rootUrl + cas)
        val document = Jsoup.parse(res)
        val tables = document.getElementsByAttributeValue("cellpadding", "7")
        var upstream = ""
        var downstream = ""
        val stream = Stream()
        stream.CAS = cas

        for (table in tables) {

            val tds = table.getElementsByTag("td")
            for (i in tds.indices) {

                if (tds[i].text() == "上游原料：") {
                    upstream = tds[i + 1].text()
                    stream.upstream = upstream
                }
                if (tds[i].text() == "下游产品：") {
                    downstream = tds[i + 1].text()
                    stream.downstream = downstream
                }
            }
        }
        streamRepo.save(stream)
//        val chemicalInfo = ChemicalInfo()
//        chemicalInfo.cas = cas
//        chemicalInfo.aliasCn = aliasCn
//        chemicalInfo.aliasEn = aliasEn
//        chemicalInfo.molecularWeight = mole
//        chemicalInfo.density = density
//        chemicalInfo.meltingPoint = melt
//        chemicalInfo.boilingPoint = boil
//        chemicalInfo.flashPoint = falsh
//        chemicalInfo.waterSolubility = solub
//        chemicalInfo.tension = tension
//        chemicalInfo.dangerMark = dangerMark
//        chemicalInfo.riskCode = riskCode
//        chemicalInfo.safeCode = safeCode
//        chemicalInfoService!!.insert(chemicalInfo)
    }
}
