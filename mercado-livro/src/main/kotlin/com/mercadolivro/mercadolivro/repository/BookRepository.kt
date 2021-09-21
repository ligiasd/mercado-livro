package com.mercadolivro.mercadolivro.repository

import com.mercadolivro.mercadolivro.enums.BookStatus
import com.mercadolivro.mercadolivro.model.BookModel
import org.springframework.data.repository.CrudRepository

interface BookRepository: CrudRepository<BookModel, Int> {
    abstract fun findByStatus(status: BookStatus): List<BookModel>
}