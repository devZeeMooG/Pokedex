package com.zeemoog.testpokeapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.zeemoog.testpokeapi.ui.PokeApp
import com.zeemoog.testpokeapi.ui.screens.detail.DetailPokemon
import com.zeemoog.testpokeapi.ui.screens.main.LoadPokeList
import com.zeemoog.testpokeapi.ui.theme.TestPokeApiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // prueba desde newnav
            PokeApp()
        }
    }
}