package com.example.bean

import javax.persistence.*

@Entity
@Table(name = "chemical_name")
data class ChemicalName(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long?,
        var CAS: String?,
        var name: String?
) {
    constructor() : this(null, null, null)
}
