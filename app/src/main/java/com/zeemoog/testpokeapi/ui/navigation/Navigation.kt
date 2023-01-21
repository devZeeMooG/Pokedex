package com.zeemoog.testpokeapi.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.zeemoog.testpokeapi.ui.screens.detail.DetailPokemon
import com.zeemoog.testpokeapi.R
import com.zeemoog.testpokeapi.ui.screens.allpoke.AllPokesScreen
import com.zeemoog.testpokeapi.ui.screens.firepoke.FirePokesScreen


/**
 * Contiene un navCommand (comando de navegacion o ruta)
 * y recurso string que identifica tipo o en general
 */
enum class NavItem(
    val navCommand: NavCommand,
    @StringRes val typeName: Int
) {
    ALL(NavCommand.ContentType(Feature.POKEMONES), R.string.all),
    FIRE(NavCommand.ContentType(Feature.FIRE), R.string.fire),
    WATER(NavCommand.ContentType(Feature.WATER), R.string.water)
}


/**
 * - Determina la ruta padre y el grafo de navegacion
 * - tambien llamado, Navegacion anidada o Grafo de navegacion anidado
 */
@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun Navigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Feature.POKEMONES.route
    ) {
        // grafo de navegacion
        pokemonesNav(navController)
        firePokesNav(navController)
    }
}

@ExperimentalMaterialApi
@ExperimentalFoundationApi
private fun NavGraphBuilder.pokemonesNav(navController: NavHostController) {
    /**
     * navigation es el q se encarga de crear el grafo de navegacion
     */
    navigation(
        startDestination = NavCommand.ContentType(Feature.POKEMONES).route,
        route = Feature.POKEMONES.route
    ) {

        composable(NavCommand.ContentType(Feature.POKEMONES)) {
            /**
             * hacemos aqui la navegacion al detalle del poke clickeado
             * - evitamos que haga esa tarea la ui, sino deberiamos pasar
             *  el navController por toda la app (no es correcto, dificil de mantener)
             */
            AllPokesScreen(
                onPokeClick = { pokemon ->
                    navController.navigate(
                        NavCommand.ContentDetail(Feature.POKEMONES).createNavRoute(pokemon.id)
                    )
                }
            )
        }

        composable(NavCommand.ContentDetail(Feature.POKEMONES)) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt(NavArg.PokemonId.key)
            requireNotNull(id)

            DetailPokemon(id)
        }

    }
}


@ExperimentalMaterialApi
@ExperimentalFoundationApi
private fun NavGraphBuilder.firePokesNav(navController: NavHostController) {

    navigation(
        startDestination = NavCommand.ContentType(Feature.FIRE).route,
        route = Feature.FIRE.route
    ) {
        composable(NavCommand.ContentType(Feature.FIRE)) {
            FirePokesScreen(
                onPokeClick = { pokemon ->
                    navController.navigate(
                        NavCommand.ContentDetail(Feature.FIRE).createNavRoute(pokemon.id)
                    )
                }
            )
        }

        composable(NavCommand.ContentDetail(Feature.FIRE)) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt(NavArg.PokemonId.key)
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