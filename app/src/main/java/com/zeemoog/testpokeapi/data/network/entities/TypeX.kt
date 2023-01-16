package com.zeemoog.testpokeapi.data.network.entities

data class TypeX(
    val name: String,
    val url: String
) {

    /**val id: Int
        get() {
            val components = url.split("/")
            return components[components.size - 2].toIntOrNull() ?: 0
        }*/

}

