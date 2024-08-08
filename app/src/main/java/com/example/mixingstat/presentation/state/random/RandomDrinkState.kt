package com.example.mixingstat.presentation.state.random

import com.example.mixingstat.data.models.Cocktail

/**
 * Data class representing the state of the Random Drink screen.
 *
 * @property showCocktail A boolean indicating whether to show the selected cocktail on the screen.
 * @property selectedCocktail The cocktail selected to be displayed on the Random Drink screen.
 * @property errorMessage The error message to display on the Random Drink screen in case of an error.
 */
data class RandomDrinkState(
    val showCocktail: Boolean = false,
    val selectedCocktail: Cocktail?,
    val errorMessage: String? = null,
)