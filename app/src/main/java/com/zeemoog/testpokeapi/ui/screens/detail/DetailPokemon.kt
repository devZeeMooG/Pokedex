package com.zeemoog.testpokeapi.ui.screens.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.zeemoog.testpokeapi.data.model.Pokemon
import com.zeemoog.testpokeapi.data.repositories.PokemonsRepository


@Composable
fun DetailPokemon(pokemonId: Int) {

    /**
     * recuperamos el 'pokemon' para q no se repita la peticion de buscar
     *  cada vez q repintemos, para eso usamos 'remember'
     *  - 'mutableStateOf<ApiPokemon?>(null)' es como inicializar
     *          un objeto de clase pokemon vacio
     */
    var pokemonState by remember { mutableStateOf<Pokemon?>(null) }
    LaunchedEffect(Unit) {
        pokemonState = PokemonsRepository.getPokemon(pokemonId)
    }

    /**
     * como pokemonState no puede ser null
     */
    pokemonState?.let { pokemon -> PokemonDetailScreen(pokemon)}

}


@Composable
fun PokemonDetailScreen(pokemon: Pokemon) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(pokemon.sprite),
                contentDescription = pokemon.name,
                contentScale = ContentScale.Crop, //la imagen ocupa todo el espacio
                modifier = Modifier
                    .clip(MaterialTheme.shapes.large) // redondea la imagen
                    .background(Color.LightGray)
                    .fillMaxWidth()
                    .aspectRatio(1f) //de alto ocupa lo mismo q el ancho
            )
            Text(text = "Name: ${pokemon.name}")
            Text(text = "Number: ${pokemon.order}")
            Text(text = "Weight: ${pokemon.weight} lb")
            Text(text = "Height: ${pokemon.height} foot")
            Text(text = "Type: ${pokemon.types.toString()
                .replace("[", "")
                .replace("]", "")}"
            )

        }
    }
}