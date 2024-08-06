package com.example.mixingstat.presentation.viewmodel.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mixingstat.config.NetworkStatus
import com.example.mixingstat.data.repository.CocktailRepository
import com.example.mixingstat.presentation.state.home.HomeScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for the HomeScreen. This ViewModel is responsible for managing the state of the HomeScreen and loading cocktails from the repository.
 *
 * @property repository The repository from which cocktails are loaded.
 * @property _state The mutable state flow that represents the state of the HomeScreen.
 * @property state The state flow that represents the state of the HomeScreen. This is what UI components should observe.
 */
@HiltViewModel
open class  HomeScreenViewModel @Inject constructor(
    private val repository: CocktailRepository
) : ViewModel() {
    private val _state = MutableStateFlow(HomeScreenState())
    open val state: StateFlow<HomeScreenState> = _state


    /**
     * Initializes the ViewModel by loading cocktails and observing the network status.
     */
    init {
        loadCocktails()
        observeNetworkStatus()
    }

    /**
     * Observes the network status and loads cocktails when the network is connected.
     */
    private fun observeNetworkStatus() {
        viewModelScope.launch {
            NetworkStatus.isConnected.collect { isConnected ->
                if (isConnected) {
                    loadCocktails()
                }
            }
        }
    }

    /**
     * Loads cocktails from the repository and updates the state.
     */
    private fun loadCocktails() {
        viewModelScope.launch {
            _state.value = HomeScreenState(isLoading = true)
            val popularCocktails = repository.getAllPopularCocktails()
            val latestCocktails = repository.getAllLatestCocktails()
            val suggestionCocktail = repository.getRandomCocktail()
            _state.value =
                HomeScreenState(popularCocktails, latestCocktails, suggestionCocktail, false)
        }
    }
}