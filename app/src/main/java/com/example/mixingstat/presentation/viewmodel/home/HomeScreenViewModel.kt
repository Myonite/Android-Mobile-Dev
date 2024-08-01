package com.example.mixingstat.presentation.viewmodel.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mixingstat.data.repository.CocktailRepository
import com.example.mixingstat.presentation.state.home.HomeScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val repository: CocktailRepository
) : ViewModel() {
    private val _state = MutableStateFlow(HomeScreenState())
    val state: StateFlow<HomeScreenState> = _state

    init {
        loadCocktails()
    }

    private fun loadCocktails() {
        viewModelScope.launch {
            _state.value = HomeScreenState(isLoading = true)
            val popularCocktails = repository.getAllPopularCocktails()
            val latestCocktails = repository.getAllLatestCocktails()
            val suggestionCocktail = repository.getRandomCocktailFromAPi()
            _state.value =
                HomeScreenState(popularCocktails, latestCocktails, suggestionCocktail, false)
        }
    }
}