package com.example.Pixzeleria.msclientes.controller

import com.example.Pixzeleria.msclientes.model.Cliente
import com.example.Pixzeleria.msclientes.service.ClienteService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin(origins = ["*"])
class ClienteController(private val clienteService: ClienteService) {

    @PostMapping("/registro")
    fun registrar(@RequestBody cliente: Cliente): ResponseEntity<Cliente> {
        return try {
            val nuevo = clienteService.registrar(cliente)
            ResponseEntity.ok(nuevo)
        } catch (e: Exception) {
            ResponseEntity.badRequest().build() // Por si hay email duplicado
        }
    }

    @PostMapping("/login")
    fun login(@RequestBody credenciales: Map<String, String>): ResponseEntity<Cliente> {
        val email = credenciales["email"]
        val pass = credenciales["password"]

        if (email != null && pass != null) {
            val cliente = clienteService.login(email, pass)
            if (cliente != null) {
                return ResponseEntity.ok(cliente)
            }
        }
        return ResponseEntity.status(401).build() // USTED NO ENTRA AQUÍ SEÑOR
    }

    @PostMapping("/guardar")
    fun guardarPerfil(@RequestBody cliente: Cliente): ResponseEntity<Cliente> {
        return ResponseEntity.ok(clienteService.actualizar(cliente))
    }

    @DeleteMapping("/{id}")
    fun eliminar(@PathVariable id: Long): ResponseEntity<Void> {
        clienteService.eliminar(id)
        return ResponseEntity.noContent().build()
    }
}