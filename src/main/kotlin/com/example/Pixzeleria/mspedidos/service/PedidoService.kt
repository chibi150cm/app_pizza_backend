package com.example.Pixzeleria.mspedidos.service

import com.example.Pixzeleria.mspedidos.model.Pedido
import com.example.Pixzeleria.mspedidos.repository.PedidoRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal
import java.util.Optional

@Service
@Transactional
class PedidoService(
    private val pedidoRepository: PedidoRepository
) {

    fun obtenerTodos(): List<Pedido> {
        return pedidoRepository.findAll()
    }

    fun guardarPedido(pedido: Pedido): Pedido {
        var sumaTotal = BigDecimal.ZERO

        pedido.items.forEach { item ->
            val subtotal = item.precio.multiply(BigDecimal(item.cantidad))
            sumaTotal = sumaTotal.add(subtotal)
            item.pedido = pedido
        }

        pedido.total = sumaTotal

        return pedidoRepository.save(pedido)
    }

    fun actualizarEstado(id: Long, nuevoEstado: String): Pedido {
        val pedido = pedidoRepository.findById(id).orElseThrow {
            RuntimeException("Pedido no encontrado con ID: $id")
        }
        pedido.estado = nuevoEstado
        return pedidoRepository.save(pedido)
    }

    fun obtenerPorCliente(clienteId: Long): List<Pedido> {
        return pedidoRepository.findByClienteId(clienteId)
    }

    fun eliminar(id: Long) {
        pedidoRepository.deleteById(id)
    }

}