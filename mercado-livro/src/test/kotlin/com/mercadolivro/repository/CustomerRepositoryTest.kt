package com.mercadolivro.repository

import com.mercadolivro.helper.buildCustomer
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

//@SpringBootTest(classes = [MercadoLivroApplicationTests::class],
//    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@ContextConfiguration
@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(MockKExtension::class)
class CustomerRepositoryTest {

    @Autowired
    private lateinit var customerRepository: CustomerRepository

    @BeforeEach
    fun setup() = customerRepository.deleteAll()

    @Test
    fun `should return name containing`() {
        val marcos = customerRepository.save(buildCustomer(name = "Marcos"))
        val matheus = customerRepository.save(buildCustomer(name = "Matheus"))
        customerRepository.save(buildCustomer(name = "Alex"))

        val customers = customerRepository.findByNameContaining("Ma")

        assertEquals(listOf(marcos, matheus), customers)
    }

    @Nested
    inner class `exists by email` {
        @Test
        fun `should return true when email exists`() {
            val email = "email@teste.com"
            customerRepository.save(buildCustomer(email = email))

            val exists = customerRepository.existsByEmail(email)

            Assertions.assertTrue(exists)
        }

        @Test
        fun `should return false when email do not exists`() {
            val email = "nonexistingemail@teste.com"

            val exists = customerRepository.existsByEmail(email)

            Assertions.assertFalse(exists)
        }
    }

    @Nested
    inner class `find by email` {
        @Test
        fun `should return customer when email exists`() {
            val email = "email@teste.com"
            val customer = customerRepository.save(buildCustomer(email = email))

            val result = customerRepository.findByEmail(email)

            Assertions.assertNotNull(result)
            assertEquals(customer, result)
        }

        @Test
        fun `should return null when email do not exists`() {
            val email = "nonexistingemail@teste.com"

            val result = customerRepository.findByEmail(email)

            Assertions.assertNull(result)
        }
    }
}