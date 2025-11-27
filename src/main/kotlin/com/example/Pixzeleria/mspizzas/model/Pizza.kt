package com.example.Pixzeleria.mspizzas.model

import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@Table(name = "pizzas")
data class Pizza(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false, length = 100)
    val nombre: String,

    @Column(columnDefinition = "TEXT")
    val descripcion: String? = null,

    @Column(nullable = false, precision = 10, scale = 2)
    val precio: BigDecimal,

    @Column(name = "imagen_url", length = 255)
    val imagenUrl: String? = null,

    @Column(length = 20)
    val tamaño: String,

    @Column(nullable = false)
    val disponible: Boolean = true,

    @Column(length = 50)
    val categoria: String? = null,

    @Column(name = "tiempo_preparacion")
    val tiempoPreparacion: Int = 30, // minutos

    @Column(name = "created_at", nullable = false, updatable = false)
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @Column(name = "updated_at")
    val updatedAt: LocalDateTime = LocalDateTime.now()
)

@Entity
@Table(name = "ingredientes")
data class Ingrediente(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false, unique = true, length = 100)
    val nombre: String,

    @Column(length = 50)
    val tipo: String? = null,

    val calorias: Int? = null,

    val vegetariano: Boolean = false
)

@Entity
@Table(name = "pizza_ingredientes")
data class PizzaIngrediente(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "pizza_id", nullable = false)
    val pizzaId: Long,

    @Column(name = "ingrediente_id", nullable = false)
    val ingredienteId: Long,

    @Column(length = 50)
    val cantidad: String = "Normal"
)