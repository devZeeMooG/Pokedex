package com.zeemoog.testpokeapi.ui.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument

/**
 * Va a definir todas las navegaciones posibles (No es GENERICA)

sealed class NavCommand(
    val baseRoute: String,
    val navArgs: List<NavArg> = emptyList() // lista de argumentos de navegacion
) {

    /**
     * en este caso las clases selladas son 'object' xq no reciben argumentos
     */

    // aqui ya defenimos su ruta ppal
    object Main: NavCommand(baseRoute = "main")

    /**
     * - como necesita esta ruta de argumento/s, creamos una clase enum
     *      q se encargue de ello
     * - 'listOf(NavArg.PokemonId)' es un argumento
     */
    object Detail: NavCommand(
        baseRoute = "detail",
        navArgs = listOf(NavArg.PokemonId)
    ) {
        /**
         * en base un pokeId, devuelve una ruta de navegacion
         */
        fun createNavRoute(pokeId: Int) = "$baseRoute/$pokeId"
    }


    /**
     * - se encargara de crear la ruta del tipo:
     *      baseRoute/{arg1}/{arg2}/{arg3}..etc
     * - usamos 'run', xq como es un poco compleja la funcion
     *      queda mas legible entre llaves el codigo
     */
    val route = run {
        // a cada argumento le mapea las llaves
        val argKeys = navArgs.map { "{${it.key}}" }

        // termina de definir el formato de la ruta y argumentos
        listOf(baseRoute)
            .plus(argKeys)
            .joinToString("/")
    }

    /** esto simplifica o equivale a lo siguiente:
     *   - listOf(navArgument("pokemonId") { type = NavType.IntType})
     */
    val args = navArgs.map {
        navArgument(it.key) { type = it.navType }
    }

} */


/**
 * Define las navegaciones de forma (GENERICA)
 *  - para esto se utiliza un concepto llamado 'FEATURE'
 */
sealed class NavCommand(
    internal val feature: Feature,
    /**
     * una ruta q define, q parte de la feature estamos viendo
     *  - x ejemplo, si es pantalla ppal(home) o detalle
     *  - x defecto defenimos q sea siempre home
     */
    internal val subRoute: String = "home",
    private val navArgs: List<NavArg> = emptyList() // lista de argumentos de navegacion
) {

    /**
     * ahora en vez de tener objetos, vamos a generalizar con class
     */
    class ContentType(feature: Feature): NavCommand(feature)

    class ContentDetail(feature: Feature): NavCommand(feature, "detail", listOf(NavArg.PokemonId)) {
        /**
         * en base un pokeId, devuelve una ruta de navegacion
         */
        fun createNavRoute(pokeId: Int) = "${feature.route}/$subRoute/$pokeId"
    }


    /** class ContentByType(feature: Feature): NavCommand(feature, "home", listOf(NavArg.PokemonType)) {
        /**
         * en base un pokeId, devuelve una ruta de navegacion
         */
        fun createNavRoute(pokeType: String) = "${feature.route}/$subRoute/$pokeType"
    } **/


    /**
     * - se encargara de crear la ruta del tipo:
     *      baseRoute/{arg1}/{arg2}/{arg3}..etc
     * - usamos 'run', xq como es un poco compleja la funcion
     *      queda mas legible entre llaves el codigo
     */
    val route = run {
        // a cada argumento le mapea las llaves
        val argKeys = navArgs.map { "{${it.key}}" }

        // termina de definir el formato de la ruta y argumentos
        listOf(feature.route, subRoute)
            .plus(argKeys)
            .joinToString("/")
    }

    /** esto simplifica o equivale a lo siguiente:
     *   - listOf(navArgument("pokemonId") { type = NavType.IntType})
     */
    val args = navArgs.map {
        navArgument(it.key) { type = it.navType }
    }

}

/**
 * ademas de definir el argumento, se define el tipo
 *  - y como puede ser cualquiera, usamos <*>
 */
enum class NavArg(val key: String, val navType: NavType<*>) {
    PokemonId("pokemonId", NavType.IntType),
    PokemonType("pokeType", NavType.StringType)
}
