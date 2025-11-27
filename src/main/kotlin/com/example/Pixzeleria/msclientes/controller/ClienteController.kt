package com.example.Pixzeleria.msclientes.controller

import com.example.Pixzeleria.msclientes.model.Cliente
import com.example.Pixzeleria.msclientes.service.ClienteService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin(origins = ["*"])
class ClienteController(private val clienteService: ClienteService) {

    @PostMapping("/guardar")
    fun guardarPerfil(@RequestBody cliente: Cliente): ResponseEntity<Cliente> {
        // Buscamos si ya existe algún cliente en la base de datos
        val listaClientes = clienteService.listar()

        return if (listaClientes.isEmpty()) {
            // Si no hay nadie (porque lo borré, jiji), creamos uno nuevo.
            val nuevo = clienteService.guardar(cliente)
            ResponseEntity.ok(nuevo)
        } else {
            // Si alguien, pos se actualiza
            val existente = listaClientes.first()
            val actualizado = cliente.copy(id = existente.id)
            ResponseEntity.ok(clienteService.guardar(actualizado))
        }
    }

    @DeleteMapping("/{id}")
    fun eliminar(@PathVariable id: Long): ResponseEntity<Void> {
        val lista = clienteService.listar()
        if (lista.isNotEmpty()) {
            clienteService.eliminar(lista.first().id!!)
        }
        return ResponseEntity.noContent().build()
    }

    @GetMapping
    fun listar(): ResponseEntity<List<Cliente>> = ResponseEntity.ok(clienteService.listar())
}