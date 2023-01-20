package com.zeemoog.testpokeapi.ui.screens.main


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.zeemoog.testpokeapi.data.model.Pokemon
import com.zeemoog.testpokeapi.data.repositories.PokemonsRepository
import com.zeemoog.testpokeapi.ui.screens.common.PokemonMainScreen


@Composable
fun LoadPokeList(onPokeClick: (Pokemon) -> Unit) {
    var pokemonListState by remember { mutableStateOf(emptyList<Pokemon>()) }

    /**
     * para usar 'getPokemonList()' de la interfaz 'PokemonRepository'
     *  necesitamos de un contexto de corrutinas, para eso usamos 'LaunchedEffect()'
     *  ya q es una 'funcion de suspension'
     */

    /** LaunchedEffect(Unit) {
        pokemonListState = if (actionNameType == "all") {
            PokemonsRepository.getPokemonList()  // lista de pokes en gral
        } else {
            PokemonsRepository.getPokemonListByType(actionNameType) // traemos lista de poke x tipo
        }
    } **/

    LaunchedEffect(Unit) {
        pokemonListState = PokemonsRepository.getPokemonList()
    }

    pokemonListState.forEach {
        println("--------------- ALL: ${it.name}")
    }

    
    PokemonMainScreen(
        pokemons = pokemonListState,
        onPokeClick = onPokeClick
    )
}


/**@Composable
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
                onClick = { onPokeClick(it) }
            )
        }
    }
} **/


/**@Composable
fun PokemonItem(
    pokemon: Pokemon,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier.padding(8.dp)
    ) {
        Text(
            text = pokemon.name,
            style = MaterialTheme.typography.subtitle1,
            maxLines = 2 //x si hay nombres largos
        )
        Card {
            Image(
                painter = rememberAsyncImagePainter(pokemon.sprite),
                contentDescription = pokemon.name,
                contentScale = ContentScale.Crop, //la imagen ocupa todo el espacio
                modifier = Modifier
                    .background(Color.LightGray)
                    .fillMaxWidth()
                    .aspectRatio(1f) //de alto ocupa lo mismo q el ancho
                    /**
                     * - ya no queremos una ruta de navegacion aqui
                     * - de eso se ocupa Navigation
                     * - para eso vamos a utilizar un 'onClick' defenido
                     *      como argumento en la funcion
                     */
                    .clickable(onClick = onClick)
            )
        }
    }
} **/