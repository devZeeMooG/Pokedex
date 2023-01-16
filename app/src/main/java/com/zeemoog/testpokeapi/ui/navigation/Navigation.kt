package com.zeemoog.testpokeapi.ui.navigation

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.zeemoog.testpokeapi.ui.screens.detail.DetailPokemon
import com.zeemoog.testpokeapi.ui.screens.main.LoadPokeList

/** Navigation 'SIN' implementacion de NavCommand con Feature
@Composable
fun Navigation(navController: NavHostController) {

    /** Navegacion entra pantallas aplicando Funciones de extension
     *  - reduce codigo y repeticion en la navegacion
     */

    /** val navController = rememberNavController()
     *  - lo sacamos de aqui para instanciarlo en PokeApp
     *  - para tener info de la ruta sobre la q estamos
     */

    NavHost(
        navController = navController,
        startDestination = NavCommand.Main.route
    ) {
        /** composable(route = NavCommand.Main.route) {
         *  - sin funcion de extension del composable
         */
        // usando funcion de extension de composable (no necesitamos detallar route y args)
        composable(NavCommand.Main) {
            /**
             * - ahora vamos a evitar pasar por todos lados el navController
             * - ya q evitamos q un click en 'ui' sobre un poke sea el q tenga
             *      la ruta de navegacion
             * - y sea Navigation quien se ocupe de dicha tarea
             */
            //LoadPokeList(navController)
            LoadPokeList { pokemon ->
                /**
                 * ahora la ruta '"detail/${pokemon.id}"'
                 * la definimos en NavCommand
                 */
                navController.navigate(NavCommand.Detail.createNavRoute(pokemon.id))
            }
        }

        /**
         * pasamos como argumentos los parametros que necesitamos para pintar
         * - "detail/{pokemonId}" -> el id es obligatorio (no null)
         * - "detail?pokemonId={pokemonId}" -> se pasa o no (null)
         * - es necesario indicar de q tipo es el argumento
         * - como la ruta es compleja -> "detail/{pokemonId}"
         *      y no ensucie el codigo, lo manejamos en NavCommand
         */
        /** composable(
         *     route = NavCommand.Detail.route,
         *     arguments = NavCommand.Detail.args
         *  ) { backStackEntry ->
         *  - sin funcion de extension composable
         */
        // usando funcion de extension de composable (no necesitamos detallar route y args)
        composable(NavCommand.Detail) { backStackEntry ->

            /**
             * para recuperar el 'pokemonId' usamos el 'it (navBackStackEntry)'
             * - se puede renombrar, en este caso lo llame 'backStackEntry'
             */
            val id = backStackEntry.arguments?.getInt(NavArg.PokemonId.key) //"pokemonId"

            /**
             * ocurre un problema, que el id puede ser/o no null en la declaracion
             * por lo tanto, como sabemos q no va a ser null lo solucionamos con
             * - requireNotNull(id)
             *      - ademas permite agregar un msj
             *      - (id, {no puede ser nulo xq ... })
             */
            requireNotNull(id)

            DetailPokemon(id)
        }
    }

} **/


/** Navigation 'CON' implementacion de NavCommand y Feature
 *  - navegacion generica
 */
@Composable
fun Navigation(navController: NavHostController, actionNameType: String) {

    /** Navegacion entra pantallas aplicando Funciones de extension
     *  - reduce codigo y repeticion en la navegacion
     */

    /** val navController = rememberNavController()
     *  - lo sacamos de aqui para instanciarlo en PokeApp
     *  - para tener info de la ruta sobre la q estamos
     */

    /** 'SIN' aplicar un grafo de navegacion
    NavHost(
        navController = navController,
        startDestination = NavCommand.ContentType(Feature.POKEMONES).route  //usando Feature
    ) {
        pokemonesNav(navController)
    } **/

    /** 'CON' aplicacion de un grafo de navegacion exclusivo
     *  - se aplica sobre 'startDestination'
     *  - se instancia sobre la funcion de extension del 'NavGraphBuilder.pokemonesNav'
     */

    NavHost(
        navController = navController,
        /** aplicando el grafo de navegacion
         *  - en donde ahora carga la ruta de navegation, y no la del home
         */
        startDestination = Feature.POKEMONES.route
    ) {
        pokemonesNav(navController, actionNameType)
    }

}

/** 'SIN' instanciar o aplicar un grafo de navegacion exclusivo
private fun NavGraphBuilder.pokemonesNav(navController: NavHostController) {
    /** composable(route = NavCommand.Main.route) {
     *  - sin funcion de extension del composable
     */

    // usando funcion de extension de composable (no necesitamos detallar route y args)
    composable(NavCommand.ContentType(Feature.POKEMONES)) {
        /**
         * - ahora vamos a evitar pasar por todos lados el navController
         * - ya q evitamos q un click en 'ui' sobre un poke sea el q tenga
         *      la ruta de navegacion
         * - y sea Navigation quien se ocupe de dicha tarea
         */
        //LoadPokeList(navController)
        LoadPokeList { pokemon ->
            /**
             * ahora la ruta '"detail/${pokemon.id}"'
             * la definimos en NavCommand
             */
            navController.navigate(
                NavCommand.ContentDetail(Feature.POKEMONES).createNavRoute(pokemon.id)
            )
        }
    }

    /**
     * pasamos como argumentos los parametros que necesitamos para pintar
     * - "detail/{pokemonId}" -> el id es obligatorio (no null)
     * - "detail?pokemonId={pokemonId}" -> se pasa o no (null)
     * - es necesario indicar de q tipo es el argumento
     * - como la ruta es compleja -> "detail/{pokemonId}"
     *      y no ensucie el codigo, lo manejamos en NavCommand
     */
    /** composable(
     *     route = NavCommand.Detail.route,
     *     arguments = NavCommand.Detail.args
     *  ) { backStackEntry ->
     *  - sin funcion de extension composable
     */
    // usando funcion de extension de composable (no necesitamos detallar route y args)
    composable(NavCommand.ContentDetail(Feature.POKEMONES)) { backStackEntry ->

        /**
         * para recuperar el 'pokemonId' usamos el 'it (navBackStackEntry)'
         * - se puede renombrar, en este caso lo llame 'backStackEntry'
         */
        val id = backStackEntry.arguments?.getInt(NavArg.PokemonId.key) //"pokemonId"

        /**
         * ocurre un problema, que el id puede ser/o no null en la declaracion
         * por lo tanto, como sabemos q no va a ser null lo solucionamos con
         * - requireNotNull(id)
         *      - ademas permite agregar un msj
         *      - (id, {no puede ser nulo xq ... })
         */
        requireNotNull(id)

        DetailPokemon(id)
    }
} **/

/** 'CON' aplicacion del grafo de navegacion exclusivo **/
private fun NavGraphBuilder.pokemonesNav(navController: NavHostController, actionNameType: String) {
    navigation(
        startDestination = NavCommand.ContentType(Feature.POKEMONES).route, //la navegacion comienza desde 'home'
        route = Feature.POKEMONES.route // define la ruta padre de todos los composables q esten dentro
    ) {
        /** todos estos 'composables' son especificos de navigation
         *  - la cual, esta navegation tiene una ruta padre (o principal) q las engloba
         */

        /** composable(route = NavCommand.Main.route) {
         *  - sin funcion de extension del composable
         */

        // usando funcion de extension de composable (no necesitamos detallar route y args)
        composable(NavCommand.ContentType(Feature.POKEMONES)) {
            /**
             * - ahora vamos a evitar pasar por todos lados el navController
             * - ya q evitamos q un click en 'ui' sobre un poke sea el q tenga
             *      la ruta de navegacion
             * - y sea Navigation quien se ocupe de dicha tarea
             */
            //LoadPokeList(navController)


            println("------------------------: $actionNameType")

            LoadPokeList(
                actionNameType,
                onPokeClick = { pokemon ->
                    /**
                     * ahora la ruta '"detail/${pokemon.id}"'
                     * la definimos en NavCommand
                     */
                    navController.navigate(
                        NavCommand.ContentDetail(Feature.POKEMONES).createNavRoute(pokemon.id)
                    )
                }
            )
        }

        /**
         * pasamos como argumentos los parametros que necesitamos para pintar
         * - "detail/{pokemonId}" -> el id es obligatorio (no null)
         * - "detail?pokemonId={pokemonId}" -> se pasa o no (null)
         * - es necesario indicar de q tipo es el argumento
         * - como la ruta es compleja -> "detail/{pokemonId}"
         *      y no ensucie el codigo, lo manejamos en NavCommand
         */
        /** composable(
         *     route = NavCommand.Detail.route,
         *     arguments = NavCommand.Detail.args
         *  ) { backStackEntry ->
         *  - sin funcion de extension composable
         */
        // usando funcion de extension de composable (no necesitamos detallar route y args)
        composable(NavCommand.ContentDetail(Feature.POKEMONES)) { backStackEntry ->

            /**
             * para recuperar el 'pokemonId' usamos el 'it (navBackStackEntry)'
             * - se puede renombrar, en este caso lo llame 'backStackEntry'
             */
            val id = backStackEntry.arguments?.getInt(NavArg.PokemonId.key) //"pokemonId"

            /**
             * ocurre un problema, que el id puede ser/o no null en la declaracion
             * por lo tanto, como sabemos q no va a ser null lo solucionamos con
             * - requireNotNull(id)
             *      - ademas permite agregar un msj
             *      - (id, {no puede ser nulo xq ... })
             */
            requireNotNull(id)

            DetailPokemon(id)
        }

    }
}


/**
 * Funciones de extension
 *  - ayudara a mejorar la navegacion para no repetir tanto codigo
 *  - ya que siempre estamos extrayendo elementos de NavCommand
 *      en los 'composoble' de NavHost
 *      - route y args (q a veces son vacios)
 *  - entonces creamos un 'composoble' q acepte x defecto un NavCommand
 *      - para q funcione dentro del Navhost, debe ser una funcion de 'NavGraphBuilder'
 */

private fun NavGraphBuilder.composable(
    /**
     * seria lo q tiene como argumentos el composable (original, no este de extension)
     *  - x ej, composable(route = NavCommand.Detail.route, arguments = NavCommand.Detail.args)
     */
    navCommand: NavCommand,
    /**
     * es el contenido del composable
     *  - x ej:
     *      composable(route = NavCommand.Main.route) { it: NavBackStackEntry (CONTENT)
     *          LoadPokeList { pokemon ->
     *              navController.navigate(NavCommand.Detail.createNavRoute(pokemon.id))
     *          }
     *      }
     */
    content: @Composable (NavBackStackEntry) -> Unit
) {
    composable(
        route = navCommand.route,
        arguments = navCommand.args
    ) {
        content(it)
    }
}