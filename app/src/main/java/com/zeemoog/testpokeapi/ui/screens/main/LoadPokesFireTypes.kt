package com.zeemoog.testpokeapi.ui.screens.main

import androidx.compose.runtime.*
import com.zeemoog.testpokeapi.data.model.Pokemon
import com.zeemoog.testpokeapi.data.repositories.PokemonsRepository
import com.zeemoog.testpokeapi.ui.screens.common.PokemonMainScreen


@Composable
fun LoadPokesFireTypes(onPokeClick: (Pokemon) -> Unit) {
    var firePokemonListState by remember { mutableStateOf(emptyList<Pokemon>()) }

    LaunchedEffect(Unit) {
        firePokemonListState = PokemonsRepository.getPokemonListByType("fire")
    }

    firePokemonListState.forEach {
        println("--------------- FIRE: ${it.name}")
    }

    PokemonMainScreen(
        pokemons = firePokemonListState,
        onPokeClick = onPokeClick
    )

}