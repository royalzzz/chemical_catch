package com.example.controller.sorting

import com.example.bean.ChemicalName
import com.example.repository.ChemicalNameRepo
import com.example.repository.DownstreamRepo
import com.example.repository.UpstreamRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("relation")
class RelationController {

    @Autowired
    lateinit var upstreamRepo: UpstreamRepo
    @Autowired
    lateinit var downstreamRepo: DownstreamRepo

    @Autowired
    lateinit var chemicalNameRepo: ChemicalNameRepo

    @RequestMapping("up")
    fun up() {
        val upList = upstreamRepo.findAll()
        for (upstream in upList) {
            val res: List<ChemicalName> = chemicalNameRepo.findByName(upstream.matter!!)
            if (res.isNotEmpty()) {
                upstream.matterCAS = res[0].CAS
                upstreamRepo.save(upstream)
            }
        }
    }

    @RequestMapping("down")
    fun down() {
        val downList = downstreamRepo.findAll()
        for (downstream in downList) {
            val res: List<ChemicalName> = chemicalNameRepo.findByName(downstream.matter!!)
            if (res.isNotEmpty()) {
                downstream.matterCAS = res[0].CAS
                downstreamRepo.save(downstream)
            }
        }
    }
}