package com.zeemoog.testpokeapi.ui.screens.allpoke


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.*
import com.zeemoog.testpokeapi.data.model.Pokemon
import com.zeemoog.testpokeapi.ui.screens.common.PokemonMainScreen
import androidx.lifecycle.viewmodel.compose.viewModel


/** @Composable
fun LoadPokeList(onPokeClick: (Pokemon) -> Unit) {
    var pokemonListState by remember { mutableStateOf(emptyList<Pokemon>()) }

    /**
     * para usar 'getPokemonList()' de la interfaz 'PokemonRepository'
     *  necesitamos de un contexto de corrutinas, para eso usamos 'LaunchedEffect()'
     *  ya q es una 'funcion de suspension'
     */

    LaunchedEffect(Unit) {
        pokemonListState = PokemonsRepository.getPokemonList()  // lista de pokes en gral
    }
    
    PokemonMainScreen(
        pokemons = pokemonListState,
        onPokeClick = onPokeClick
    )
} **/

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun AllPokesScreen(onPokeClick: (Pokemon) -> Unit, viewModel: AllPokesViewModel = viewModel()) {
    val state by viewModel.state.collectAsState()
    PokemonMainScreen(
        pokemons = state.items,
        onPokeClick = onPokeClick
    )
}