package com.example.repository

import com.example.bean.Upstream
import org.springframework.data.jpa.repository.JpaRepository

interface UpstreamRepo : JpaRepository<Upstream, Long> {

}