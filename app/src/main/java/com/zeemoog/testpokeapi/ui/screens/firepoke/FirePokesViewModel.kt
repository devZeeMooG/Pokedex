package com.zeemoog.testpokeapi.ui.screens.firepoke

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zeemoog.testpokeapi.data.model.Pokemon
import com.zeemoog.testpokeapi.data.repositories.PokemonByTypeRepository
import com.zeemoog.testpokeapi.data.repositories.PokemonsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FirePokesViewModel: ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.value = UiState(loading = true)
            _state.value = UiState(items = PokemonByTypeRepository.getPokemonListByType("fire"))
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val items: List<Pokemon> = emptyList()
    )

}