package com.zeemoog.testpokeapi.data.model

/*
el paquete 'model'
    tambien puede ser llamado 'entities'
 */

data class Pokemon(
    val id: Int,
    val order: Int,
    val name: String,
    val weight: Int?,
    val height: Int?,
    val sprite: String?,
    val types: List<String>?
)
