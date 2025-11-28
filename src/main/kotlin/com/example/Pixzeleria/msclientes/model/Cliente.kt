package com.example.Pixzeleria.msclientes.model

import jakarta.persistence.*

@Entity
@Table(name = "clientes")
data class Cliente(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val nombre: String,
    @Column(unique = true)
    val email: String,
    val password: String,
    val telefono: String? = null,
    val direccion: String? = null,
    val rol: String = "USER"
)