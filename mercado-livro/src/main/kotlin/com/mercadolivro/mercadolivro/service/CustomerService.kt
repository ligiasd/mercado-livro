package com.mercadolivro.mercadolivro.service


import com.mercadolivro.mercadolivro.enums.CustomerStatus
import com.mercadolivro.mercadolivro.enums.Errors
import com.mercadolivro.mercadolivro.enums.Role
import com.mercadolivro.mercadolivro.exception.NotFoundException
import com.mercadolivro.mercadolivro.model.CustomerModel
import com.mercadolivro.mercadolivro.repository.CustomerRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service


@Service
class CustomerService(
    private val customerRepository: CustomerRepository,
    private val bookService: BookService,
    private val bCrypt: BCryptPasswordEncoder

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
        val customerCopy = customer.copy(
            roles = setOf(Role.CUSTOMER),
            password = bCrypt.encode(customer.password)
        )
        customerRepository.save(customerCopy)
    }

    fun findById(id: Int): CustomerModel{
        return customerRepository.findById(id).orElseThrow{ NotFoundException( Errors.ML201.message.format(id), Errors.ML201.code) }
    }

    fun update(customer: CustomerModel){
        if(!customerRepository.existsById(customer.id!!)){
            throw NotFoundException( Errors.ML201.message.format(customer.id), Errors.ML201.code)
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

    fun emailAvailable(email: String): Boolean {
        return !customerRepository.existsByEmail(email)

    }


}