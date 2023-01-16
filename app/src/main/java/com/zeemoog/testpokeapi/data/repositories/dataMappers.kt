package com.zeemoog.testpokeapi.data.repositories

import com.zeemoog.testpokeapi.data.model.Pokemon
import com.zeemoog.testpokeapi.data.network.entities.ApiPokemon

/**
 * Consiste en mappear los datos del service en datos del dominio (model)
 *  - a su vez es una funcion de extension de ApiPokemon (network/entities)
 */
fun ApiPokemon.asPokemon(): Pokemon = Pokemon(
    id,
    order,
    name,
    weight,
    height,
    sprites.front_default,
    types.map { it.type.name }
)