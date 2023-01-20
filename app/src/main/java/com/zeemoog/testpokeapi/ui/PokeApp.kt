package com.zeemoog.testpokeapi.ui


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.zeemoog.testpokeapi.ui.navigation.Navigation
import com.zeemoog.testpokeapi.ui.screens.common.TypesMenu
import com.zeemoog.testpokeapi.ui.theme.TestPokeApiTheme

@Composable
fun PokeApp() {

    var typesMenuOpen by remember { mutableStateOf(false) }
    //var actionNameType by remember { mutableStateOf("All") }

    //var navItem by remember { mutableStateOf(NavItem.ALL) }

    /**
    /**
     * permite saber en q parte de la pantalla estamos
     * - por eso lo saque de Navigation
     */
    //val navController = rememberNavController()
    val navController: NavHostController = rememberNavController()

    /**
     * permite acceder a la ruta actual
     *  - ademas recibido como estado, si cambia la ruta se vuelve a llamar
     */
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    /**
     *  la ruta puede venir vacia, entonces devolvemos cadena vacia
     *  - ademas porque al principio antes de cargar algo
     *      es posible q la ruta sea vacia
     */
    val currentRoute = navBackStackEntry?.destination?.route ?: ""

    */

    val appState = rememberPokeAppState()


    PokeAppScreen {

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = "Pokedex") },
                    navigationIcon = {
                        // si la ruta actual es home, muestra menu sino estamos en detalle
                        if (/**currentRoute.contains(NavCommand.ContentType(Feature.POKEMONES).route)**/ true) {
                            IconButton(onClick = { /*Menu*/ }) {
                                Icon(
                                    imageVector = Icons.Default.Menu,
                                    contentDescription = "Go to Menu"
                                )
                            }
                        } else {
                            IconButton(onClick = { /**navController.popBackStack()*/ }) {
                                Icon(
                                    imageVector = Icons.Default.ArrowBack,
                                    contentDescription = "Go to previous Screen"
                                )
                            }
                        }
                    },
                    actions = {
                        IconButton(onClick = { typesMenuOpen = true }) {
                            Icon(
                                imageVector = Icons.Default.MoreVert,
                                contentDescription = "see types pokemon"
                            )
                            TypesMenu(
                                expanded = typesMenuOpen,
                                //onItemClick = { actionNameType = it },
                                navOptions = PokeAppState.TYPE_NAV_OPTIONS,
                                onNavItemClick = { appState.onNavItemClick(it) },
                                onDismiss = { typesMenuOpen = false }
                            )
                        }
                    }
                )
            },
            scaffoldState = rememberScaffoldState()
        ) { padding ->
            // como scaffold devuelve un padding es necesario asignarlo al contenido, por eso uso un box
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


/** sin aplicar el if q determina si mostrar menu o arrowback
PokeAppScreen {

Scaffold(
topBar = {
TopAppBar(
title = { Text(text = "Pokedex") },
navigationIcon = {
if (true) {
IconButton(onClick = { /*Menu*/ }) {
Icon(
imageVector = Icons.Default.Menu,
contentDescription = "Go to Menu"
)
}
} else {
IconButton(onClick = { /*ArrowBack*/ }) {
Icon(
imageVector = Icons.Default.ArrowBack,
contentDescription = "Go to previous Screen"
)
}
}
}
)
}
) { padding ->
Navigation()
}

} */