package com.zeemoog.testpokeapi.data.network.entities

/**
 * modifico el nombre a ApiPokemon (antes solo Pokemon)
 * para diferenciarlo del Pokemon del paquete model
 */
data class ApiPokemon(
    val id: Int,
    val name: String,
    val order: Int,
    val height: Int,
    val weight: Int,
    val types: List<Type>,
    val sprites: Sprites
    /** //val abilities: List<Ability>,
    //val base_experience: Int,
    //val forms: List<Form>,
    //val game_indices: List<GameIndice>,
    //val held_items: List<HeldItem>,
    //val is_default: Boolean,
    //val location_area_encounters: String,
    //val moves: List<Move>,
    //val past_types: List<PastType>,
    //val species: Species,
    //val stats: List<Stat>,
    ////val types: List<TypeXX>, **/
)