package com.zeemoog.testpokeapi.data.network.entities

data class PokeApiResponse(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<PokeResult>
)