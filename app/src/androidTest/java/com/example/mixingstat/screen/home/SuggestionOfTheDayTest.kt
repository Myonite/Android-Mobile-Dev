package com.example.mixingstat.screen.home

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.example.mixingstat.data.models.Cocktail
import com.example.mixingstat.ui.screen.home.SuggestionOfTheDay
import org.junit.Rule
import org.junit.Test

class SuggestionOfTheDayTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun suggestionOfTheDay_displaysCorrectly() {
        val cocktail = Cocktail(
            idDrink = "1",
            strDrink = "Test Cocktail",
            strDrinkThumb = "https://example.com/image.jpg",
            strCategory = "Test Category",
            strAlcoholic = "Test Alcoholic",
            strGlass = "Test Glass"
        )

        composeTestRule.setContent {
            SuggestionOfTheDay(cocktail) {}
        }

        composeTestRule.onNodeWithText("Test Cocktail").assertExists()
        composeTestRule.onNodeWithText("Category: Test Category").assertExists()
        composeTestRule.onNodeWithText("Alcoholic: Test Alcoholic").assertExists()
        composeTestRule.onNodeWithText("Glass: Test Glass").assertExists()
    }
}