package com.mercadolivro.mercadolivro.service


import com.mercadolivro.mercadolivro.enums.CustomerStatus
import com.mercadolivro.mercadolivro.exception.NotFoundException
import com.mercadolivro.mercadolivro.model.CustomerModel
import com.mercadolivro.mercadolivro.repository.CustomerRepository
import org.springframework.stereotype.Service


@Service
class CustomerService(
    val customerRepository: CustomerRepository,
    val bookService: BookService

) {
    val customers = mutableListOf<CustomerModel>()

    fun getAll(name: String?): List<CustomerModel>{
        try {
            name?.let {
                return customerRepository.findByNameContaining(name)
            }
            return customerRepository.findAll().toMutableList()
        }
        catch (e: Exception)
        {
            throw e
        }

    }


    fun create(customer: CustomerModel){
        customerRepository.save(customer)
    }

    fun findById(id: Int): CustomerModel{
        return customerRepository.findById(id).orElseThrow{ NotFoundException( "Custumer ${id} not exists", "ML-0002") }
    }

    fun update(customer: CustomerModel){
        if(!customerRepository.existsById(customer.id!!)){
            throw Exception()
        }
        customerRepository.save(customer)
    }

    fun delete(id: Int){
        val customer = findById(id)
        bookService.deleteByCustomer(customer)

        customer.status = CustomerStatus.INATIVO

        customerRepository.save(customer)

//        if(!customerRepository.existsById(id!!)){
//            throw Exception()
//        }
//        customerRepository.deleteById(id)
    }



}