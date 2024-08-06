package com.example.mixingstat.screen.home

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import com.example.mixingstat.data.models.Cocktail
import com.example.mixingstat.ui.screen.home.ImageSlider
import org.junit.Rule
import org.junit.Test


class ImageSliderTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun whenCocktailsIsEmpty_showErrorMessage() {
        composeTestRule.setContent {
            ImageSlider(cocktails = emptyList(), navigateTo = {})
        }

        composeTestRule.onNodeWithTag("ErrorMessage").assertIsDisplayed()
    }

    @Test
    fun whenCocktailsIsNotEmpty_showCocktails() {
        val cocktails = listOf(
            Cocktail(
                idDrink = "1",
                strDrink = "Test Cocktail",
                strDrinkThumb = "https://www.thecocktaildb.com/images/media/drink/metwgh1606770327.jpg"
            )
        )

        composeTestRule.setContent {
            ImageSlider(cocktails = cocktails, navigateTo = {})
        }

        composeTestRule.onNodeWithTag("ErrorMessage").assertDoesNotExist()
        composeTestRule.onNodeWithTag("CocktailCard1", useUnmergedTree = true).performClick()
    }

    @Test
    fun whenCocktailCardIsClicked_navigateToIsCalled() {
        var route = ""
        val navigateTo: (String) -> Unit = { route = it }

        val cocktails = listOf(
            Cocktail(
                idDrink = "1",
                strDrink = "Test Cocktail",
                strDrinkThumb = "https://www.thecocktaildb.com/images/media/drink/metwgh1606770327.jpg"
            )
        )

        composeTestRule.setContent {
            ImageSlider(cocktails = cocktails, navigateTo = navigateTo)
        }

        composeTestRule.onNodeWithTag("CocktailCard1",useUnmergedTree = true).performClick()

        assert(route == "cocktail/1")
    }

}