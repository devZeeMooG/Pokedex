package com.zeemoog.testpokeapi.ui.screens.common

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.zeemoog.testpokeapi.data.model.Pokemon

@Composable
fun PokemonMainScreen(
    pokemons: List<Pokemon>,
    onPokeClick: (Pokemon) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(180.dp),
        contentPadding = PaddingValues(4.dp)
    ) {
        items(pokemons) {
            PokemonItem(
                pokemon = it,
                /**
                 * aqui hace la navegacion al detalle del poke
                 * - pero como la ui solo debe pintar, le quitamos esa tarea
                 * - y lo hacemos en Navigation
                 */
                onClick = { onPokeClick(it) }
            )
        }
    }
}