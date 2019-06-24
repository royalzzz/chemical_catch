package com.example.repository

import com.example.bean.Chemicals
import org.springframework.data.jpa.repository.JpaRepository

interface ChemicalRepo : JpaRepository<Chemicals, Long>