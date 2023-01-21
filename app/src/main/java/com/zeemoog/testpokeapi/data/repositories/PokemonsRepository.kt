package com.zeemoog.testpokeapi.data.repositories

import com.zeemoog.testpokeapi.data.model.Pokemon
import com.zeemoog.testpokeapi.data.network.ApiClient
import com.zeemoog.testpokeapi.data.network.entities.PokeApiResponse
import com.zeemoog.testpokeapi.data.network.entities.PokeResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// obtiene lista de pokes para luego usarse en ui

object PokemonsRepository : Repository<Pokemon>() {

    /** 'SIN' aplicar Repository
     *  - el cual cachea las peticiones para no repetirlas innecesariamente
     *  suspend fun getPokemonList(): List<ApiPokemon> {
            val result = ApiClient.pokeService.getPokemonList(150, 0)

            return result.results.map {
            getPokemon(it.id)
            }
        }
     *
     *  suspend fun getPokemonListByType(name: String): List<ApiPokemon> {
            val result = ApiClient.pokeService.getPokemonListByType(name)

            return result.pokemon.map {
            getPokemon(it.pokemon.id)
            }
        }
     *
     *  suspend fun getPokemon(id: Int): Pokemon {
            val result = ApiClient.pokeService.getPokemon(id)
            val listType: MutableList<String> = mutableListOf()
            result.types.map {
                listType.add(it.type.name)
            }
            return Pokemon(
                result.id,
                result.order,
                result.name,
                result.weight,
                result.height,
                result.sprites.front_default,
                listType
            )
        }
     */

    // aplicando Repository (uso de cache)

    suspend fun getPokemonList(): List<Pokemon> = super.get {

        println("------------------ QUERY BY ALL")

        ApiClient
            .pokeService
            .getPokemonList(20, 0)
            .results
            .map { getPokemon(it.id) }
    }


    /** suspend fun getPokemonListByType(name: String): List<Pokemon> = super.get {

        println("------------------ QUERY BY TYPE")

        ApiClient
            .pokeService
            .getPokemonListByType(name)
            .pokemon.map { getPokemon(it.pokemon.id) }
    } **/


    suspend fun getPokemon(id: Int): Pokemon = super.find(
        id,
        findActionRemote = {
            ApiClient
                .pokeService
                .getPokemon(id)
                .asPokemon()
        }
    )

}