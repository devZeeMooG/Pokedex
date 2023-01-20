package com.zeemoog.testpokeapi.ui

import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.zeemoog.testpokeapi.ui.navigation.NavItem
import com.zeemoog.testpokeapi.ui.navigation.navigatePoppingUpToStartDestination
import kotlinx.coroutines.CoroutineScope


@Composable
fun rememberPokeAppState(
    navController: NavHostController = rememberNavController()
): PokeAppState = remember(navController) {
    PokeAppState(navController)
}


class PokeAppState(val navController: NavHostController) {

    // Constantes
    companion object {
        // declaramos q vamos a mostrar en el bottombar
        val TYPE_NAV_OPTIONS = listOf(NavItem.ALL, NavItem.FIRE, NavItem.WATER)
    }


    // Properties
    val currentRoute: String
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination?.route ?: ""


    // Logica de UI
    fun onNavItemClick(navItem: NavItem) {
        navController.navigatePoppingUpToStartDestination(navItem.navCommand.route)
    }

}