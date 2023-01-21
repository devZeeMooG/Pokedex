package com.zeemoog.testpokeapi.data.repositories

import com.zeemoog.testpokeapi.data.model.Pokemon
import com.zeemoog.testpokeapi.data.network.ApiClient

/**
 * PROBLEMA:
 * - cuando se inicia app, carga listado de pokes en general (tiene su object repository)
 * - dropdownmenu se carga correctamente
 * - selecciono un tipo poke, no carga listado de pokes por tipo y repite llamada a listado en general
 *
 * SOLUCION:
 * - crear un object repository exclusivo para cuando se haga un llamada por tipo de poke
 * - ahora no se solapan las llamadas a la api, y se pinta correctamente la pantalla
 */
object PokemonByTypeRepository: Repository<Pokemon>() {

    /**
     * obtiene listado de pokes por tipo (se pasa el tipo)
     */
    suspend fun getPokemonListByType(name: String): List<Pokemon> = super.get {
        ApiClient
            .pokeService
            .getPokemonListByType(name)
            .pokemon.map { PokemonsRepository.getPokemon(it.pokemon.id) }
    }

}