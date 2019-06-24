package com.example.bean

import javax.persistence.*

@Entity
@Table(name = "chemicals")
data class Chemicals(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long?,
        var CAS: String?,
        var nameCn: String?,
        var nameEn: String?,
        var MF: String?
) {
    constructor() : this(null, null, null, null, null)
}