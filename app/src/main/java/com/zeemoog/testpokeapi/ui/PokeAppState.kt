package com.zeemoog.testpokeapi.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.zeemoog.testpokeapi.ui.navigation.Feature
import com.zeemoog.testpokeapi.ui.navigation.NavCommand
import com.zeemoog.testpokeapi.ui.navigation.NavItem
import com.zeemoog.testpokeapi.ui.navigation.navigatePoppingUpToStartDestination


/**
 * - creo un remember personalizado
 * - es necesario q sea @composable, para poder acceder a funciones composables
 * - "remember(..)" tiene argumentos los cuales, si alguno de ellos cambia el
 *      PokeAppState se vuelve a generar
 */
@Composable
fun rememberPokeAppState(
    navController: NavHostController = rememberNavController()
): PokeAppState = remember(navController) {
    PokeAppState(navController)
}


class PokeAppState(val navController: NavHostController) {

    // Constantes
    companion object {
        /**
         * listado de navItems
         * para la navegacion entre pokes de distintos tipos (o en general)
         */
        val TYPE_NAV_OPTIONS = listOf(NavItem.ALL, NavItem.FIRE/**, NavItem.WATER**/)
    }


    // Properties

    /** BackStackEntry
     * es el q permite acceder a la ruta actual
     * Y necesita de una funcion composable para poder usarse (@Composable get()) **/

    /** si viene alguna ruta vacia, devolvemos cadena vacia **/
    val currentRoute: String
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination?.route ?: ""


    /** si estamos en pantalla ppal no muestra btn arrowback, y si
     * esta detalle lo muestra  **/
    val showUpNavigation: Boolean
        @Composable get() = currentRoute !in NavItem.values().map { it.navCommand.route }


    // Logica de UI
    fun onUpClick() {
        navController.popBackStack()
    }

    /**
     * se encarga de la navegacion, dependiendo del tipo poke seleccionado
     */
    fun onNavItemClick(navItem: NavItem) {
        navController.navigatePoppingUpToStartDestination(navItem.navCommand.route)
    }

}