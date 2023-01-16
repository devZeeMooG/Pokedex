package com.zeemoog.testpokeapi.data.network.entities

data class DamageRelationsX(
    val double_damage_from: List<DoubleDamageFrom>,
    val double_damage_to: List<DoubleDamageTo>,
    val half_damage_from: List<HalfDamageFrom>,
    val half_damage_to: List<HalfDamageTo>,
    val no_damage_from: List<NoDamageFrom>,
    val no_damage_to: List<NoDamageTo>
)