package com.zeemoog.testpokeapi.ui.screens.common

import android.widget.Toast
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun TypesMenu(
    expanded: Boolean,
    onItemClick: (String) -> Unit,
    onDismiss: () -> Unit
) {

    val options = listOf(
        "All",
        "Fire",
        "Water",
        "Grass",
        "Ghost"
    )

    DropdownMenu(
        expanded = expanded,
        onDismissRequest = onDismiss
    ) {
        options.forEach { type ->
            DropdownMenuItem(
                onClick = {
                    onItemClick(type)
                    onDismiss()
                }
            ) {
                Text(text = type)
            }
        }
    }
}