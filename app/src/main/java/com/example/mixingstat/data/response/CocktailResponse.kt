package com.example.mixingstat.data.response

import com.example.mixingstat.data.models.Cocktail

/**
 * Data class representing the response from the Cocktail API.
 *
 * @property drinks The list of cocktails returned by the API.
 */
data class CocktailsResponse(
    val drinks: List<Cocktail>
)