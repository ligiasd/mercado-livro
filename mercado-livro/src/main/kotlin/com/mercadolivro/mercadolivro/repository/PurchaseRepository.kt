package com.mercadolivro.mercadolivro.repository

import com.mercadolivro.mercadolivro.model.PurchaseModel
import org.springframework.data.repository.CrudRepository

interface PurchaseRepository: CrudRepository<PurchaseModel, Int> {

}
