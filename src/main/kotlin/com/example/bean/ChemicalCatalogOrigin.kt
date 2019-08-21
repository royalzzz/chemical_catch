package com.example.bean

import javax.persistence.*

@Entity
@Table(name = "chemical_catalog_origin")
data class ChemicalCatalogOrigin(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long = -1,
        var cas: String,
        var result: String
)