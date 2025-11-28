package com.example.Pixzeleria.msclientes.repository

import com.example.Pixzeleria.msclientes.model.Cliente
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface ClienteRepository : JpaRepository<Cliente, Long> {
    fun findByEmail(email: String): Optional<Cliente>
}