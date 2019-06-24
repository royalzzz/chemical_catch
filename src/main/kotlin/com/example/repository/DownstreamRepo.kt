package com.example.repository

import com.example.bean.Downstream
import org.springframework.data.jpa.repository.JpaRepository

interface DownstreamRepo : JpaRepository<Downstream, Long>