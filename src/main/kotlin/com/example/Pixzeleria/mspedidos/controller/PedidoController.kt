package com.example.Pixzeleria.mspedidos.controller

import com.example.Pixzeleria.mspedidos.model.Pedido
import com.example.Pixzeleria.mspedidos.service.PedidoService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/pedidos")
@CrossOrigin(origins = ["*"])
class PedidoController(
    private val pedidoService: PedidoService
) {

    @GetMapping
    fun listarPedidos(): ResponseEntity<List<Pedido>> {
        return ResponseEntity.ok(pedidoService.obtenerTodos())
    }

    @PostMapping
    fun crearPedido(@RequestBody pedido: Pedido): ResponseEntity<Pedido> {
        val nuevoPedido = pedidoService.guardarPedido(pedido)
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoPedido)
    }

    @GetMapping("/cliente/{id}")
    fun listarPorCliente(@PathVariable id: Long): ResponseEntity<List<Pedido>> {
        return ResponseEntity.ok(pedidoService.obtenerPorCliente(id))
    }

    @DeleteMapping("/{id}")
    fun eliminarPedido(@PathVariable id: Long): ResponseEntity<Void> {
        return try {
            pedidoService.eliminar(id)
            ResponseEntity.noContent().build()
        } catch (e: Exception) {
            ResponseEntity.notFound().build()
        }
    }

}