package com.example.Pixzeleria.msclientes.service

import com.example.Pixzeleria.msclientes.model.Cliente
import com.example.Pixzeleria.msclientes.repository.ClienteRepository
import org.springframework.stereotype.Service
import java.security.MessageDigest

@Service
class ClienteService(private val clienteRepository: ClienteRepository) {

    // Aquí se "hashea" la contraseña
    private fun hashPassword(pass: String): String {
        val bytes = pass.toByteArray()
        val md = MessageDigest.getInstance("SHA-256")
        val digest = md.digest(bytes)
        return digest.fold("") { str, it -> str + "%02x".format(it) }
    }

    fun registrar(cliente: Cliente): Cliente {
        // Se encripta la contraseña antes de guardars
        val clienteSeguro = cliente.copy(password = hashPassword(cliente.password))
        return clienteRepository.save(clienteSeguro)
    }

    fun login(email: String, pass: String): Cliente? {
        val clienteOpt = clienteRepository.findByEmail(email)
        if (clienteOpt.isPresent) {
            val cliente = clienteOpt.get()
            // Comparamos el hash de lo que llega con el hash guardao
            if (cliente.password == hashPassword(pass)) {
                return cliente
            }
        }
        return null
    }

    fun obtenerPorId(id: Long): Cliente? {
        return clienteRepository.findById(id).orElse(null)
    }
    fun listar(): List<Cliente> = clienteRepository.findAll()

    fun actualizar(cliente: Cliente): Cliente {
        // Si la password viene vacía o igual, mantenemos la lógica
        return clienteRepository.save(cliente)
    }

    fun eliminar(id: Long) = clienteRepository.deleteById(id)

}