package com.zeemoog.testpokeapi.ui.screens.allpoke


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.*
import com.zeemoog.testpokeapi.data.model.Pokemon
import com.zeemoog.testpokeapi.ui.screens.common.PokemonMainScreen
import androidx.lifecycle.viewmodel.compose.viewModel


/**
 * VIEWMODEL
 * - para acceder al viewmodel, lo agrego como argumento
 * - es necesario agregar a mano a imports el viewmodel
 * - androidx.lifecycle.viewmodel.compose.viewModel
 */
/**
 * manejo el ESTADO con STATE FLOW
 */
@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun AllPokesScreen(onPokeClick: (Pokemon) -> Unit, viewModel: AllPokesViewModel = viewModel()) {
    /**
     * aqui se nota el "STATE HOSTING"
     * - "estados" viajan hacia abajo
     * - "eventos" hacia arriba
     */
    val state by viewModel.state.collectAsState()
    PokemonMainScreen(
        pokemons = state.items,
        onPokeClick = onPokeClick
    )
}