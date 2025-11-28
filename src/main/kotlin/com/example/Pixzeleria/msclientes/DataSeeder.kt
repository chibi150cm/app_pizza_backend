package com.example.Pixzeleria.msclientes

import com.example.Pixzeleria.msclientes.model.Cliente
import com.example.Pixzeleria.msclientes.service.ClienteService
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class DataSeeder(private val clienteService: ClienteService) : CommandLineRunner {

    override fun run(vararg args: String?) {
        val emailAdmin = "admin@pixzeleria.com"

        val clientes = clienteService.listar()
        val existeAdmin = clientes.any { it.email == emailAdmin }

        if (!existeAdmin) {
            val admin = Cliente(
                nombre = "Mara Boss",
                email = emailAdmin,
                password = "admin.123",
                telefono = "987408508",
                direccion = "Avenida Siempreviva 745",
                rol = "ADMIN"
            )

            clienteService.registrar(admin)

            println("¡Usuario Admin creado exitosamente!")
            println("📧 Email: $emailAdmin")
            println("🔑 Pass:  admin.123")
        } else {
            println("El usuario Admin ya existe en la base de datos.")
        }
    }
}