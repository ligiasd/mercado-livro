package com.mercadolivro.mercadolivro.controller.response

class PageResponse<T> (
    var itens: List<T>,
    var currentPages: Int,
    var totalItens: Long,
    var totalPages: Int,


)