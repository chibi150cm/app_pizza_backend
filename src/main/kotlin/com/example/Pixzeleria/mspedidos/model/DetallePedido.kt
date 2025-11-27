package com.example.Pixzeleria.mspedidos.model

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "pedido_detalles")
data class DetallePedido(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "nombre_pizza", nullable = false)
    val nombrePizza: String,

    @Column(name = "pizza_id")
    val pizzaId: Long? = null,

    @Column(nullable = false)
    val precio: BigDecimal,

    @Column(nullable = false)
    val cantidad: Int = 1,

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    @JsonIgnore
    var pedido: Pedido? = null
)