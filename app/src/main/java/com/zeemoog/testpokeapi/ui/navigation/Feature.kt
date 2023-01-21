package com.zeemoog.testpokeapi.ui.navigation

/**
 * Concepto utilizado para navegaciones genericas
 *  - este contiene una ruta principal de navegacion
 */
enum class Feature(val route: String) {
    // plural de pokemon en ingles es 'pokemon' (muy confuso)
    POKEMONES("pokemones"),
    FIRE("fire"),
    WATER("water")

    /** aqui debajo irian mas tipos navegaciones, por ej:
     *  - MOVIES("movies")
     *  -SERIES("series")
     */
}