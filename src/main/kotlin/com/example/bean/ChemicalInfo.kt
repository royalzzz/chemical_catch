package com.example.bean

import javax.persistence.*

@Entity
@Table(name = "chemical_info")
data class ChemicalInfo(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long?,
        var cas: String?,
        var alias_cn: String?,
        var alias_en: String?,
        var molecular_weight: String?,
        var density: String?,
        var melting_point: String?,
        var boiling_point: String?,
        var flash_point: String?,
        var water_solubility: String?,
        var tension: String?,
        var danger_mark: String?,
        var risk_code: String?,
        var safe_code: String?
) {
    constructor() : this(null, null, null, null, null, null, null, null, null, null, null, null, null, null)
}