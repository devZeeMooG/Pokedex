package com.zeemoog.testpokeapi.data.repositories

import com.zeemoog.testpokeapi.data.model.Pokemon
import com.zeemoog.testpokeapi.data.network.ApiClient

object PokemonByTypeRepository: Repository<Pokemon>() {

    suspend fun getPokemonListByType(name: String): List<Pokemon> = super.get {

        println("------------------ QUERY BY TYPE")

        ApiClient
            .pokeService
            .getPokemonListByType(name)
            .pokemon.map { PokemonsRepository.getPokemon(it.pokemon.id) }
    }

}