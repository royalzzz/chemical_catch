package com.example.repository

import com.example.bean.ChemicalInfo
import org.springframework.data.jpa.repository.JpaRepository

interface ChemicalInfoRepo : JpaRepository<ChemicalInfo, Long> {

    fun findOneByCas(cas: String): ChemicalInfo
}