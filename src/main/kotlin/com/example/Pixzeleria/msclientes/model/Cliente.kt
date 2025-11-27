package com.example.Pixzeleria.msclientes.model

import jakarta.persistence.*

@Entity
@Table(name = "clientes")
data class Cliente(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val nombre: String,
    val email: String,
    val telefono: String?,
    val direccion: String?
)