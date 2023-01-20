package com.zeemoog.testpokeapi.ui.screens.common

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.zeemoog.testpokeapi.ui.navigation.NavItem

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TypesMenu(
    expanded: Boolean,
    //onItemClick: (String) -> Unit,
    navOptions: List<NavItem>,
    onNavItemClick: (NavItem) -> Unit,
    onDismiss: () -> Unit
) {
    /**val options = listOf(
        "All",
        "Fire",
        "Water",
        "Grass",
        "Ghost"
    ) **/

    //val typesPoke = listOf(NavItem.ALL, NavItem.FIRE, NavItem.WATER)

    DropdownMenu(
        expanded = expanded,
        onDismissRequest = onDismiss
    ) {
        navOptions.forEach { item ->
            val type = stringResource(id = item.title)
            DropdownMenuItem(
                onClick = {
                    //onItemClick(type)
                    onNavItemClick(item)  // paso el navItem completo (no solo el nombre del typo)
                    onDismiss()
                }
            ) {
                //Text(text = type)
                ListItem(text = { Text(text = type) })
            }
        }
    }
}