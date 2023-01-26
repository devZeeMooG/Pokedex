package com.zeemoog.testpokeapi.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.zeemoog.testpokeapi.ui.navigation.Navigation
import com.zeemoog.testpokeapi.ui.screens.common.TypesMenu
import com.zeemoog.testpokeapi.ui.theme.TestPokeApiTheme


@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun PokeApp() {

    val appState = rememberPokeAppState()

    PokeAppScreen {

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = "Pokedex") },
                    navigationIcon = {
                        if (appState.showUpNavigation) {
                            IconButton(
                                onClick = { appState.onUpClick() }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.ArrowBack,
                                    contentDescription = "Go to previous Screen"
                                )
                            }
                        } else {
                            IconButton(onClick = { /*Menu*/ }) {
                                Icon(
                                    imageVector = Icons.Default.Menu,
                                    contentDescription = "Go to Menu"
                                )
                            }
                        }
                    },
                    actions = {
                        TypesMenu(
                            typeNavOptions = PokeAppState.TYPE_NAV_OPTIONS,
                            onNavItemClick = { appState.onNavItemClick(it) }
                        )
                    }
                )
            },
            scaffoldState = rememberScaffoldState() //para gestionar el estado del menu (drawer, sin uso)
        ) { padding ->
            /**
             * scaffold devuelve un padding
             * el cual es necesario asignarlo al contenido, por eso uso un box
             */
            Box(modifier = Modifier.padding(padding)) {
                Navigation(appState.navController)
            }
        }

    }
}


@Composable
fun PokeAppScreen(content: @Composable () -> Unit) {
    TestPokeApiTheme() {
        // A surface container using the 'background' color from the theme
        Surface(color = MaterialTheme.colors.background) {
            content()
        }
    }
}