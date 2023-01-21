package com.zeemoog.testpokeapi.ui.screens.allpoke

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zeemoog.testpokeapi.data.model.Pokemon
import com.zeemoog.testpokeapi.data.repositories.PokemonsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AllPokesViewModel: ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

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