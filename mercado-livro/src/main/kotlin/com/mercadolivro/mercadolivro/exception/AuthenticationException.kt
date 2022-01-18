package com.mercadolivro.mercadolivro.exception

class AuthenticationException(override val message: String, val erroCode: String): Exception()