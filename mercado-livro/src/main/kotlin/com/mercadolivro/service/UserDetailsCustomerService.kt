package com.mercadolivro.service

import com.mercadolivro.mercadolivro.exception.AuthenticationException
import com.mercadolivro.repository.CustomerRepository
import com.mercadolivro.mercadolivro.security.UserCustomDetails
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserDetailsCustomerService(
    private val customerRepository: CustomerRepository
): UserDetailsService {
    override fun loadUserByUsername(id: String): UserDetails {
        var customer = customerRepository.findById(id.toInt())
            .orElseThrow{ AuthenticationException("Usuário não encontrado", "999") }
        return UserCustomDetails(customer)
    }

}