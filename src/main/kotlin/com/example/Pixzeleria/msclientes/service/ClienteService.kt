package com.example.Pixzeleria.msclientes.service

import com.example.Pixzeleria.msclientes.model.Cliente
import com.example.Pixzeleria.msclientes.repository.ClienteRepository
import org.springframework.stereotype.Service

@Service
class ClienteService(private val clienteRepository: ClienteRepository) {
    fun guardar(cliente: Cliente): Cliente {
        return clienteRepository.save(cliente)
    }

    fun listar(): List<Cliente> {
        return clienteRepository.findAll()
    }

    fun eliminar(id: Long) {
        clienteRepository.deleteById(id)
    }

    fun obtenerPorId(id: Long): Cliente? {
        return clienteRepository.findById(id).orElse(null)
    }
}