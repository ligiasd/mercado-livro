package com.mercadolivro.mercadolivro.events.listener

import com.mercadolivro.mercadolivro.events.PurchaseEvent
import com.mercadolivro.mercadolivro.service.BookService
import com.mercadolivro.mercadolivro.service.PurchaseService
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import java.util.*

@Component
class UpdateSoldBookListener(
    private val bookService: BookService
) {
    @Async
    @EventListener
    fun listen(purchaseEvent: PurchaseEvent){
        bookService.purchase(purchaseEvent.purchaseModel.books)
    }
}