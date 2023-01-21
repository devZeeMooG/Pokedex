package com.zeemoog.testpokeapi.ui.screens.allpoke

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zeemoog.testpokeapi.data.model.Pokemon
import com.zeemoog.testpokeapi.data.repositories.PokemonsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * extiende de ViewModel()
 * para poder usar el scope, contexto de corrutina
 * y asi lanzar funciones de suspension
 */
class AllPokesViewModel: ViewModel() {
    /**
     * manejo del ESTADO con "STATE FLOW" (recomendado por android y google)
     */

    /**
     * '_state' se recomienda privado para q no sea modificado desde afuera
     * - forma de ocultar q es un matableStateFlow
     */
    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    /**
     * bloque "init"
     * - muestra el loading hasta q cargue la lista de pokes
     */
    init {
        viewModelScope.launch {
            _state.value = UiState(loading = true)
            _state.value = UiState(items = PokemonsRepository.getPokemonList())
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val items: List<Pokemon> = emptyList()
    )

}