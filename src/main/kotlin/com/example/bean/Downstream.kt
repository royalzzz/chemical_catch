package com.example.bean

import javax.persistence.*

@Entity
@Table(name = "downstream")
data class Downstream(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long?,
        var CAS: String?,
        var matter: String?,
        var matterCAS: String?,
        var additional: String?
) {
    constructor() : this(null, null, null, null, null)
}