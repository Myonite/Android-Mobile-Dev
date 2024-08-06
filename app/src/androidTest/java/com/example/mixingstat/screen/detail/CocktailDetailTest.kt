package com.example.mixingstat.screen.detail

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.example.mixingstat.data.models.Cocktail
import com.example.mixingstat.ui.screen.detail.CocktailDetail
import org.junit.Rule
import org.junit.Test

class CocktailDetailTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun cocktailDetail_displaysCorrectly() {
        val cocktail = Cocktail(
            idDrink = "1",
            strDrink = "Test Cocktail",
            strDrinkThumb = "https://example.com/image.jpg",
            strCategory = "Test Category",
            strAlcoholic = "Test Alcoholic",
            strGlass = "Test Glass"
        )

        composeTestRule.setContent {
            CocktailDetail(cocktail) {}
        }

        composeTestRule.onNodeWithText("Test Cocktail").assertExists()
        composeTestRule.onNodeWithText("Test Category").assertExists()
        composeTestRule.onNodeWithText("Test Alcoholic").assertExists()
        composeTestRule.onNodeWithText("Test Glass").assertExists()
    }
}