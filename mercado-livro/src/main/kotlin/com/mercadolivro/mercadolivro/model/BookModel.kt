package com.mercadolivro.mercadolivro.model

import com.mercadolivro.mercadolivro.enums.BookStatus
import java.math.BigDecimal
import java.util.*
import javax.persistence.*

@Entity(name = "book")
data class BookModel (

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,

    @Column(name = "name")
    var name: String,

    @Column(name = "price")
    var price: BigDecimal,

    @Column
    @Enumerated(EnumType.STRING)
    var status: BookStatus? = null,

    @ManyToOne
    @JoinColumn(name = "customer_id")
    var customer: CustomerModel? = null

)