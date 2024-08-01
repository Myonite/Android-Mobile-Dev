package com.example.mixingstat.presentation.viewmodel.random

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mixingstat.data.repository.CocktailRepository
import com.example.mixingstat.presentation.state.random.RandomDrinkState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RandomDrinkViewModel @Inject constructor(private val repository: CocktailRepository) :
    ViewModel() {
    private val _randomDrinkState = MutableStateFlow(RandomDrinkState(false, null))
    val randomDrinkState: StateFlow<RandomDrinkState> = _randomDrinkState

    init {
        selectRandomCocktail()
    }

    private fun selectRandomCocktail() {
        viewModelScope.launch {
            val selectedCocktail = repository.getRandomCocktailFromAPi()
            if (selectedCocktail != null) {
                _randomDrinkState.value = RandomDrinkState(true, selectedCocktail)
            }
        }
    }
}