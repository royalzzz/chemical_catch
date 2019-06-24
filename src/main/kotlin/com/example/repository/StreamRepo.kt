package com.example.repository

import com.example.bean.Stream
import org.springframework.data.jpa.repository.JpaRepository

interface StreamRepo : JpaRepository<Stream, Long> {
    fun findByUpstreamIsNotNull(): List<Stream>

    fun findByDownstreamIsNotNull(): List<Stream>
}