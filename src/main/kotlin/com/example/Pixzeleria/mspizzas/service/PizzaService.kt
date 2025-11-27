package com.example.Pixzeleria.mspizzas.service

import com.example.Pixzeleria.mspizzas.model.Pizza
import com.example.Pixzeleria.mspizzas.repository.PizzaRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

interface PizzaService {
    fun obtenerTodas(): List<Pizza>
    fun obtenerPorId(id: Long): Pizza?
    fun obtenerDisponibles(): List<Pizza>
    fun obtenerPorCategoria(categoria: String): List<Pizza>
    fun buscarPorNombre(nombre: String): List<Pizza>
    fun crear(pizza: Pizza): Pizza
    fun actualizar(id: Long, pizza: Pizza): Pizza?
    fun eliminar(id: Long)
    fun cambiarDisponibilidad(id: Long, disponible: Boolean): Pizza?
}

@Service
@Transactional
class PizzaServiceImpl(
    private val pizzaRepository: PizzaRepository
) : PizzaService {

    override fun obtenerTodas(): List<Pizza> {
        return pizzaRepository.findAll()
    }

    override fun obtenerPorId(id: Long): Pizza? {
        return pizzaRepository.findById(id).orElse(null)
    }

    override fun obtenerDisponibles(): List<Pizza> {
        return pizzaRepository.findByDisponibleTrue()
    }

    override fun obtenerPorCategoria(categoria: String): List<Pizza> {
        return pizzaRepository.findByDisponibleTrueAndCategoria(categoria)
    }

    override fun buscarPorNombre(nombre: String): List<Pizza> {
        return pizzaRepository.findByNombreContainingIgnoreCase(nombre)
    }

    override fun crear(pizza: Pizza): Pizza {
        return pizzaRepository.save(pizza)
    }

    override fun actualizar(id: Long, pizza: Pizza): Pizza? {
        return if (pizzaRepository.existsById(id)) {
            pizzaRepository.save(pizza.copy(id = id))
        } else {
            null
        }
    }

    override fun eliminar(id: Long) {
        if (pizzaRepository.existsById(id)) {
            pizzaRepository.deleteById(id)
        }
    }

    override fun cambiarDisponibilidad(id: Long, disponible: Boolean): Pizza? {
        val pizza = obtenerPorId(id) ?: return null
        val pizzaActualizada = pizza.copy(disponible = disponible)
        return pizzaRepository.save(pizzaActualizada)
    }
}