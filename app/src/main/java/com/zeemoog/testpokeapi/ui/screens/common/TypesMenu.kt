package com.zeemoog.testpokeapi.ui.screens.common

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import com.zeemoog.testpokeapi.ui.navigation.NavItem

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TypesMenu(
    typeNavOptions: List<NavItem>,
    onNavItemClick: (NavItem) -> Unit
    //onNavItemClick: (String) -> Unit
) {

    var showMenu by remember { mutableStateOf(false) }

    IconButton(onClick = { showMenu = !showMenu }) {
        Icon(
            imageVector = Icons.Default.MoreVert,
            contentDescription = "More Actions"
        )
        DropdownMenu(
            expanded = showMenu,
            onDismissRequest = { showMenu = false }
        ) {
            typeNavOptions.forEach {
                val type = stringResource(id = it.typeName)
                DropdownMenuItem(
                    onClick = {
                        //onNavItemClick(type)
                        onNavItemClick(it)
                        showMenu = false
                    }
                ) {
                    ListItem(text = { Text(text = type) })
                }
            }
        }
    }


}