package com.example.controller.sorting

import com.example.bean.ChemicalInfo
import com.example.bean.ChemicalName
import com.example.repository.ChemicalInfoRepo
import com.example.repository.ChemicalNameRepo
import com.example.repository.ChemicalRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("sort")
class SortController {

    @Autowired
    lateinit var chemicalNameRepo: ChemicalNameRepo

    @Autowired
    lateinit var chemicalRepo: ChemicalRepo

    @Autowired
    lateinit var chemicalInfoRepo: ChemicalInfoRepo

    @RequestMapping("name")
    fun sortName() {
        val chemicals = chemicalRepo.findAll()
        var i = 1
        for (chemical in chemicals) {
            val primary = ChemicalName()
            primary.CAS = chemical.CAS
            primary.name = chemical.nameCn
            chemicalNameRepo.save(primary)

            val alias = chemical.CAS?.let { chemicalInfoRepo.findOneByCas(it) } as ChemicalInfo
            val aliasName = alias.alias_cn
            val aliasNames = aliasName?.split("; ".toRegex()) as List<String>
            for (a in aliasNames) {
                val temp = ChemicalName()
                temp.CAS = chemical.CAS
                temp.name = a
                chemicalNameRepo.save(temp)
            }
            println("进度： $i / ${chemicals.size}")
            i++
        }
    }
}