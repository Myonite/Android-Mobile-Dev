package com.example.mixingstat.utils

import com.example.mixingstat.dev_seeding.cocktail
import com.example.mixingstat.data.models.Cocktail

fun getCocktailById(id: String): Cocktail? {
    return cocktail.find { it.idDrink == id }
}