package com.example.controller.cutword

import com.example.bean.Downstream
import com.example.bean.Stream
import com.example.bean.Upstream
import com.example.repository.DownstreamRepo
import com.example.repository.StreamRepo
import com.example.repository.UpstreamRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("cut")
class CutController {

    @Autowired
    lateinit var streamRepo: StreamRepo
    @Autowired
    lateinit var upstreamRepo: UpstreamRepo
    @Autowired
    lateinit var downstreamRepo: DownstreamRepo

    @RequestMapping("word")
    fun cut() {
        val cs = streamRepo.findByDownstreamIsNotNull()
        for (c in cs) {
            val matters = c.downstream?.split("、".toRegex()) as List<String>

            for (m in matters) {
                val downstream = Downstream()
                downstream.CAS = c.CAS
                downstream.matter = m
                downstreamRepo.save(downstream)
            }
            println("GG")
        }
    }

    @RequestMapping("clearup")
    fun clearup() {
        val uplist = upstreamRepo.findAll()

    }

    @RequestMapping("cleardown")
    fun cleardown() {

    }

}