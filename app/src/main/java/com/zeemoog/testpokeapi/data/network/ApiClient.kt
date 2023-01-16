package com.zeemoog.testpokeapi.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

/*
    - para establecer la conexion con api
    - usamos retrofit
    - x el momento un object (singleton)
        - pero no es aconsejable, pero x ahora no usamos 'Hilt'
        - es por simplificacion
 */

const val API_ENDPOINT = "https://pokeapi.co/api/v2/"

object ApiClient {

    private val retrofit = Retrofit.Builder()
        .baseUrl(API_ENDPOINT)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    // es el q va a recuperar datos del servidor
    val pokeService: PokeApiService = retrofit.create()

}