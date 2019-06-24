package com.example.repository

import com.example.bean.ChemicalName
import org.springframework.data.jpa.repository.JpaRepository

interface ChemicalNameRepo : JpaRepository<ChemicalName, Long> {
    fun findByName(name: String): List<ChemicalName>
}