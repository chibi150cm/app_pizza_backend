package com.example.Pixzeleria.mspizzas.repository

import com.example.Pixzeleria.mspizzas.model.Pizza
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.math.BigDecimal

@Repository
interface PizzaRepository : JpaRepository<Pizza, Long> {

    fun findByDisponibleTrue(): List<Pizza>

    fun findByCategoria(categoria: String): List<Pizza>

    fun findByTamaño(tamaño: String): List<Pizza>

    fun findByDisponibleTrueAndCategoria(categoria: String): List<Pizza>

    fun findByPrecioBetween(min: BigDecimal, max: BigDecimal): List<Pizza>

    fun findByNombreContainingIgnoreCase(nombre: String): List<Pizza>
}