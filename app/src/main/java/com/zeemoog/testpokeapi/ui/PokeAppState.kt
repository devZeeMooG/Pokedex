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


@Composable
fun rememberPokeAppState(
    navController: NavHostController = rememberNavController()
): PokeAppState = remember(navController) {
    PokeAppState(navController)
}


class PokeAppState(val navController: NavHostController) {

    // Constantes
    companion object {
        val TYPE_NAV_OPTIONS = listOf(NavItem.ALL, NavItem.FIRE, NavItem.WATER)
    }

    // Properties
    val currentRoute: String
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination?.route ?: ""


    val showUpNavigation: Boolean
        @Composable get() = currentRoute !in NavItem.values().map { it.navCommand.route }


    // Logica de UI
    fun onUpClick() {
        navController.popBackStack()
    }

    /** pasando el tipo poke en vez de un navItem
    fun onNavItemClick(pokeType: String) {
        navController.navigatePoppingUpToStartDestination(pokeType)
    } **/

    fun onNavItemClick(navItem: NavItem) {
        navController.navigatePoppingUpToStartDestination(navItem.navCommand.route)
    }

    fun navigateFire() {
        navController.navigate(NavCommand.ContentType(Feature.FIRE).route)
    }

}