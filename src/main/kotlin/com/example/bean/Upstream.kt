package com.example.bean

import javax.persistence.*

@Entity
@Table(name = "upstream")
data class Upstream(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long?,
        var CAS: String?,
        var matter: String?,
        var matterCAS: String?,
        var additional: String?
) {
    constructor() : this(null, null, null, null, null)
}