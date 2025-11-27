package com.example.Pixzeleria.mspedidos.model

import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@Table(name = "pedidos")
data class Pedido(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "cliente_id", nullable = false)
    val clienteId: Long,

    @Column(nullable = false)
    val fecha: LocalDateTime = LocalDateTime.now(),

    @Column(nullable = false)
    var total: BigDecimal = BigDecimal.ZERO,

    @Column(length = 20)
    var estado: String = "PENDIENTE",

    @OneToMany(mappedBy = "pedido", cascade = [CascadeType.ALL], fetch = FetchType.LAZY, targetEntity = DetallePedido::class)
    var items: List<DetallePedido> = mutableListOf()
)