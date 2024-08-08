package com.example.mixingstat.presentation.viewmodel.random

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mixingstat.config.NetworkStatus
import com.example.mixingstat.data.repository.CocktailRepository
import com.example.mixingstat.presentation.state.random.RandomDrinkState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for the RandomDrink screen.
 *
 * @property repository The repository to fetch cocktails from.
 * @property _randomDrinkState The MutableStateFlow representing the state of the RandomDrink screen.
 * @property randomDrinkState The StateFlow representing the state of the RandomDrink screen.
 */
@HiltViewModel
class RandomDrinkViewModel @Inject constructor(
    private val repository: CocktailRepository
) : ViewModel() {
    private val _randomDrinkState = MutableStateFlow(RandomDrinkState(false, null))
    val randomDrinkState: StateFlow<RandomDrinkState> = _randomDrinkState

    init {
        selectRandomCocktail()
        observeNetworkStatus()
    }

    /**
     * Observes the network status and selects a random cocktail when connected.
     */
    private fun observeNetworkStatus() {
        viewModelScope.launch {
            NetworkStatus.isConnected.collect { isConnected ->
                if (isConnected) {
                    selectRandomCocktail()
                }
            }
        }
    }

    /**
     * Selects a random cocktail from the repository and updates the state.
     */
    private fun selectRandomCocktail() {
        viewModelScope.launch {
            val selectedCocktail = repository.getRandomCocktail()
            if (selectedCocktail != null) {
                _randomDrinkState.value = RandomDrinkState(true, selectedCocktail)
            }
        }
    }
}