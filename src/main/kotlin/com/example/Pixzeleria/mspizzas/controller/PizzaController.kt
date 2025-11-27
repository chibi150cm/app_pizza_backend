package com.example.Pixzeleria.mspizzas.controller

import com.example.Pixzeleria.mspizzas.model.Pizza
import com.example.Pixzeleria.mspizzas.service.PizzaService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/pizzas")
@CrossOrigin(origins = ["*"])
class PizzaController(
    private val pizzaService: PizzaService
) {

    // Obtener todas las pipshas
    @GetMapping
    fun obtenerTodas(): ResponseEntity<List<Pizza>> {
        val pizzas = pizzaService.obtenerTodas()
        return ResponseEntity.ok(pizzas)
    }

    // Obtener pipshas por ID
    @GetMapping("/{id}")
    fun obtenerPorId(@PathVariable id: Long): ResponseEntity<Pizza> {
        val pizza = pizzaService.obtenerPorId(id)
        return pizza?.let {
            ResponseEntity.ok(it)
        } ?: ResponseEntity.notFound().build()
    }

    // Obtener sólo las pipshas disponibles
    @GetMapping("/disponibles")
    fun obtenerDisponibles(): ResponseEntity<List<Pizza>> {
        val pizzas = pizzaService.obtenerDisponibles()
        return ResponseEntity.ok(pizzas)
    }

    // Filtrar por categoría
    @GetMapping("/categoria/{categoria}")
    fun obtenerPorCategoria(@PathVariable categoria: String): ResponseEntity<List<Pizza>> {
        val pizzas = pizzaService.obtenerPorCategoria(categoria)
        return ResponseEntity.ok(pizzas)
    }

    // Buscar por nombre de la pipsha
    @GetMapping("/buscar")
    fun buscarPorNombre(@RequestParam nombre: String): ResponseEntity<List<Pizza>> {
        val pizzas = pizzaService.buscarPorNombre(nombre)
        return ResponseEntity.ok(pizzas)
    }

    // Crear una pipsha
    @PostMapping
    fun crear(@RequestBody pizza: Pizza): ResponseEntity<Pizza> {
        val nuevaPizza = pizzaService.crear(pizza)
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaPizza)
    }

    // Actualicen la pipsha
    @PutMapping("/{id}")
    fun actualizar(
        @PathVariable id: Long,
        @RequestBody pizza: Pizza
    ): ResponseEntity<Pizza> {
        val pizzaActualizada = pizzaService.actualizar(id, pizza)
        return pizzaActualizada?.let {
            ResponseEntity.ok(it)
        } ?: ResponseEntity.notFound().build()
    }

    // Cambiar la disponibilidad de la pipsha
    @PatchMapping("/{id}/disponibilidad")
    fun cambiarDisponibilidad(
        @PathVariable id: Long,
        @RequestParam disponible: Boolean
    ): ResponseEntity<Pizza> {
        val pizza = pizzaService.cambiarDisponibilidad(id, disponible)
        return pizza?.let {
            ResponseEntity.ok(it)
        } ?: ResponseEntity.notFound().build()
    }

    // KILL LA PIZZA
    @DeleteMapping("/{id}")
    fun eliminar(@PathVariable id: Long): ResponseEntity<Void> {
        pizzaService.eliminar(id)
        return ResponseEntity.noContent().build()
    }
}