package com.zeemoog.testpokeapi.data.network.entities

data class Types(
    val damage_relations: DamageRelations,
    val game_indices: List<GameIndiceX>,
    val generation: GenerationXX,
    val id: Int,
    val move_damage_class: MoveDamageClass,
    val moves: List<MoveXX>,
    val name: String,
    val names: List<Name>,
    val past_damage_relations: List<PastDamageRelation>,
    val pokemon: List<PokemonX>
)