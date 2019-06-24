package com.example.bean

import javax.persistence.*

@Entity
@Table(name = "stream")
data class Stream(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long?,
        var CAS: String?,
        var upstream: String?,
        var downstream: String?
) {
    constructor() : this(null, null, null, null)
}