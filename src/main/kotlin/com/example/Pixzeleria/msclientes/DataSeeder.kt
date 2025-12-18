package com.example.Pixzeleria.msclientes

import com.example.Pixzeleria.msclientes.model.Cliente
import com.example.Pixzeleria.msclientes.service.ClienteService
import com.example.Pixzeleria.mspizzas.model.Pizza
import com.example.Pixzeleria.mspizzas.repository.PizzaRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import java.math.BigDecimal

@Component
class DataSeeder(
    private val clienteService: ClienteService,
    private val pizzaRepository: PizzaRepository
    ) : CommandLineRunner {

    override fun run(vararg args: String?) {
        crearAdmin()
        crearCocinero()
        crearPizzas()
    }

    private fun crearAdmin() {
        val emailAdmin = "admin@pixzeleria.com"
        val clientes = clienteService.listar()
        if (clientes.none { it.email == emailAdmin }) {
            val admin = Cliente(
                nombre = "Administrador Jefe",
                email = emailAdmin,
                password = "admin",
                telefono = "123456789",
                direccion = "Oficina Central",
                rol = "ADMIN"
            )
            clienteService.registrar(admin)
            println("✅ ADMIN CREADO")
        }
    }

    private fun crearCocinero() {
        val emailCocinero = "cocina@pixzeleria.com"
        val clientes = clienteService.listar()

        if (clientes.none { it.email == emailCocinero }) {
            val cocinero = Cliente(
                nombre = "Sanji Vinsmoke",
                email = emailCocinero,
                password = "123miau",
                telefono = "987654321",
                direccion = "Cocina del Baratie",
                rol = "COCINERO"
            )
            clienteService.registrar(cocinero)
            println("✅ COCINERO CREADO (LISTO PARA LA ACCIÓN)")
        }
    }

    private fun crearPizzas() {
        if (pizzaRepository.count() == 0L) {
            val menu = listOf(
                Pizza(
                    nombre = "Margherita Clásica",
                    descripcion = "Tomate, mozzarella fresca, albahaca",
                    precio = BigDecimal(12990),
                    imagenUrl = "margherita",
                    categoria = "Clásicas",
                    tamaño = "Familiar"
                ),
                Pizza(
                    nombre = "Pepperoni Suprema",
                    descripcion = "Doble pepperoni y extra queso",
                    precio = BigDecimal(14990),
                    imagenUrl = "pepperoni",
                    categoria = "Clásicas",
                    tamaño = "Familiar"
                ),
                Pizza(
                    nombre = "Cuatro Quesos",
                    descripcion = "Mozzarella, parmesano, gorgonzola, provolone",
                    precio = BigDecimal(15990),
                    imagenUrl = "quattro_formaggi",
                    categoria = "Gourmet",
                    tamaño = "Familiar"
                ),
                Pizza(
                    nombre = "Vegetariana Deluxe",
                    descripcion = "Pimientos, champiñones, aceitunas",
                    precio = BigDecimal(13990),
                    imagenUrl = "vegetariana",
                    categoria = "Veggies",
                    tamaño = "Familiar"
                ),
                Pizza(
                    nombre = "BBQ Chicken",
                    descripcion = "Pollo, salsa BBQ, cebolla morada",
                    precio = BigDecimal(16990),
                    imagenUrl = "bbq_chicken",
                    categoria = "Especiales",
                    tamaño = "Familiar"
                ),
                Pizza(
                    nombre = "Hawaiana",
                    descripcion = "Jamón, piña y extra queso",
                    precio = BigDecimal(13990),
                    imagenUrl = "hawaiana",
                    categoria = "Clásicas",
                    tamaño = "Familiar"
                ),
                Pizza(
                    nombre = "Italiana",
                    descripcion = "Prosciutto, rúcula y tomate cherry",
                    precio = BigDecimal(17990),
                    imagenUrl = "italiana",
                    categoria = "Gourmet",
                    tamaño = "Familiar"
                ),
                Pizza(
                    nombre = "Mexicana Picante",
                    descripcion = "Carne, jalapeños, nachos y cheddar",
                    precio = BigDecimal(15990),
                    imagenUrl = "mexicana",
                    categoria = "Especiales",
                    tamaño = "Familiar"
                )
            )

            pizzaRepository.saveAll(menu)
            println("MENÚ DE PIZZAS CREADO AUTOMÁTICAMENTE")
        } else {
            println("Las pizzas ya existen, no se hizo nada (uwu).")
        }
    }
}