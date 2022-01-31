package com.mercadolivro.controller.response

import com.mercadolivro.mercadolivro.enums.CustomerStatus

data class CustomerResponse (

    var id: Int? = null,

    var name: String,

    var email: String,

    var status: CustomerStatus

)
