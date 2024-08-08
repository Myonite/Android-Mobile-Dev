package com.example.mixingstat.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mixingstat.data.repository.CocktailRepository
import com.example.mixingstat.presentation.state.CocktailDetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for the CocktailDetail screen.
 *
 * @property repository The repository to fetch cocktails from.
 * @property _state The MutableStateFlow representing the state of the CocktailDetail screen.
 * @property state The StateFlow representing the state of the CocktailDetail screen.
 */
@HiltViewModel
class CocktailDetailViewModel @Inject constructor(
    private val repository: CocktailRepository
) : ViewModel() {
    private val _state = MutableStateFlow(CocktailDetailState())
    val state: StateFlow<CocktailDetailState> = _state

    /**
     * Searches for a cocktail by its ID and updates the state.
     *
     * @param id The ID of the cocktail to search for.
     */
    fun searchById(id: String) {
        viewModelScope.launch {
            val cocktail = repository.getCocktail(id)
            _state.value = CocktailDetailState(cocktail)
        }
    }
}