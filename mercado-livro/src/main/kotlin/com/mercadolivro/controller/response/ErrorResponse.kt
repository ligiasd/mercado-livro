package com.mercadolivro.mercadolivro.controller.response

data class ErrorResponse(
    val httpCode: Int,
    var message: String,
    var internalCode: String,
    var errors: List<FieldErrorResponse>?

)
