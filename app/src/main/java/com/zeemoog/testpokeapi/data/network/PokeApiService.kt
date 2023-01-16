package com.zeemoog.testpokeapi.data.network

import com.zeemoog.testpokeapi.data.network.entities.PokeApiResponse
import com.zeemoog.testpokeapi.data.network.entities.ApiPokemon
import com.zeemoog.testpokeapi.data.network.entities.Types
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeApiService {

    @GET("pokemon/{id}")
    suspend fun getPokemon(@Path("id") id: Int): ApiPokemon

    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): PokeApiResponse

    @GET("type/{name}")
    suspend fun getPokemonListByType(@Path("name") name: String): Types

}