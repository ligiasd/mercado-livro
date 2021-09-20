package com.mercadolivro.mercadolivro.model

import java.util.*
import javax.persistence.*

@Entity(name = "customer")
data class CustomerModel (

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,

    @Column(name = "name")
    var name: String,

    @Column(name = "email")
    var email: String,
)