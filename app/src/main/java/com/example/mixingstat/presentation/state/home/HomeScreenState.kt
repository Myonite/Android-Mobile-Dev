package com.example.mixingstat.presentation.state.home

import com.example.mixingstat.data.models.Cocktail

/**
 * Data class representing the state of the Home screen.
 *
 * @property popularCocktails The list of popular cocktails to display on the Home screen.
 * @property latestCocktails The list of the latest cocktails to display on the Home screen.
 * @property suggestionCocktail The cocktail suggested to the user on the Home screen.
 * @property isLoading A boolean indicating whether the data for the Home screen is still loading.
 */
data class HomeScreenState(
    val popularCocktails: List<Cocktail>? = null,
    val latestCocktails: List<Cocktail>? = null,
    val suggestionCocktail: Cocktail? = null,
    val isLoading: Boolean = true
)