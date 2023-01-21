package com.zeemoog.testpokeapi.ui.screens.firepoke

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.*
import com.zeemoog.testpokeapi.data.model.Pokemon
import com.zeemoog.testpokeapi.ui.screens.common.PokemonMainScreen
import androidx.lifecycle.viewmodel.compose.viewModel

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun FirePokesScreen(onPokeClick: (Pokemon) -> Unit, viewModel: FirePokesViewModel = viewModel()) {
    val state by viewModel.state.collectAsState()
    PokemonMainScreen(
        pokemons = state.items,
        onPokeClick = onPokeClick
    )
}