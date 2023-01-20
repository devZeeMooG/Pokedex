package com.zeemoog.testpokeapi.ui.screens.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.zeemoog.testpokeapi.data.model.Pokemon

@Composable
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
                    .clickable(onClick = onClick)
            )
        }
    }
}