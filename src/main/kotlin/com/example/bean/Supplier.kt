package com.example.bean

import javax.persistence.*

@Entity
@Table(name = "supplier")
data class Supplier(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long?,
        var CAS: String?,
        var company: String?,
        var phone: String?,
        var fax: String?,
        var url: String?
) {
    constructor() : this(null, null, null, null, null, null)
}