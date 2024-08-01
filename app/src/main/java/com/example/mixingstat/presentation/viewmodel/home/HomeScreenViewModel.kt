package com.example.mixingstat.presentation.viewmodel.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mixingstat.data.models.Cocktail
import com.example.mixingstat.data.repository.CocktailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val repository: CocktailRepository
) : ViewModel() {
    private val _cocktails = MutableStateFlow<List<Cocktail>>(emptyList())
    val cocktails: StateFlow<List<Cocktail>> = _cocktails

    init {
        loadPopularCocktails()
        loadLatestCocktails()
    }

    private fun loadPopularCocktails() {
        viewModelScope.launch {
            val cocktails = repository.getAllPopularCocktails()
            _cocktails.value = cocktails
        }
    }
    private fun loadLatestCocktails() {
        viewModelScope.launch {
            val cocktails = repository.getAllLatestCocktails()
            _cocktails.value = cocktails
        }
    }
}