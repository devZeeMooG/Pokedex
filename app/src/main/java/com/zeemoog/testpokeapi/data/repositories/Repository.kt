package com.zeemoog.testpokeapi.data.repositories

import com.zeemoog.testpokeapi.data.model.Pokemon

/**
 * uso de cache para no tener que estar haciendo peticiones innecesarias
 */
abstract class Repository<T: Pokemon> {

    private var cache: List<T> = emptyList()

    internal suspend fun get(getAction: suspend () -> List<T> ): List<T> {
        if (cache.isEmpty()) {
            cache = getAction()
        }
        return cache
    }

    internal suspend fun find(
        id: Int,
        findActionRemote: suspend () -> T  //accion q tiene q ejecutar
    ): T {
        // si encuentra el elemento en la cache lo devuelve
        val item = cache.find { it.id == id }
        if (item != null) return item

        // si no lo encuentra ejecuta esa accion remota
        return findActionRemote()
    }

}