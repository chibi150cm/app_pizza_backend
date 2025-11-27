package com.example.Pixzeleria.mspedidos.repository

import com.example.Pixzeleria.mspedidos.model.Pedido
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PedidoRepository : JpaRepository<Pedido, Long> {
    // Lista el historial de pedidos de un cliente
    fun findByClienteId(clienteId: Long): List<Pedido>
}