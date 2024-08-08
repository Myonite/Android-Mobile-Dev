package com.example.mixingstat.presentation.state

import com.example.mixingstat.data.models.Cocktail

/**
 * Data class representing the state of the Cocktail Detail screen.
 *
 * @property cocktail The cocktail to display on the Cocktail Detail screen.
 */
data class CocktailDetailState(
    val cocktail: Cocktail? = null
)