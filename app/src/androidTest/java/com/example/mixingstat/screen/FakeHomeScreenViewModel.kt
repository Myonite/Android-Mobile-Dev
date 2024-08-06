package com.example.mixingstat.screen

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.example.mixingstat.presentation.state.home.HomeScreenState
import com.example.mixingstat.presentation.viewmodel.home.HomeScreenViewModel
import com.example.mixingstat.data.repository.CocktailRepository
import javax.inject.Inject

class FakeHomeScreenViewModel @Inject constructor(
    fakeRepository: CocktailRepository
) : HomeScreenViewModel(fakeRepository) {
    val _state = MutableStateFlow(HomeScreenState(isLoading = true))
    override val state: StateFlow<HomeScreenState> = _state
}