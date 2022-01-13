package com.mercadolivro.mercadolivro.exception

class NotFoundException(override val message: String, val erroCode: String): Exception()  {
}