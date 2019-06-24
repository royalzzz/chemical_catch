package com.example.repository

import com.example.bean.Supplier
import org.springframework.data.jpa.repository.JpaRepository

interface SupplierRepo : JpaRepository<Supplier, Long>