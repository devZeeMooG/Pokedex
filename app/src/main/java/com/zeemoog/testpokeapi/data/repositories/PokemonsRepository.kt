package com.zeemoog.testpokeapi.data.repositories

import com.zeemoog.testpokeapi.data.model.Pokemon
import com.zeemoog.testpokeapi.data.network.ApiClient
import com.zeemoog.testpokeapi.data.network.entities.PokeApiResponse
import com.zeemoog.testpokeapi.data.network.entities.PokeResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * Se encarga de hacer las peticiones a la api
 * - obtiene listado de pokes en general (se define un maximo)
 * - obtiene un pokemon por id
 */
object PokemonsRepository : Repository<Pokemon>() {

    // aplicando Repository (uso de cache)

    /**
     * trae lista de pokes en general
     * - (con un maximo, este caso 20, se define aqui)
     */
    suspend fun getPokemonList(): List<Pokemon> = super.get {
        ApiClient
            .pokeService
            .getPokemonList(20, 0)
            .results
            .map { getPokemon(it.id) }
    }

    /**
     * devuelve un pokemon por id
     */
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