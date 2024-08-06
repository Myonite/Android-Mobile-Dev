package com.example.mixingstat.screen

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.example.mixingstat.data.models.Cocktail
import com.example.mixingstat.data.repository.CocktailRepository
import com.example.mixingstat.ui.screen.home.HomeScreen
import com.example.mixingstat.presentation.state.home.HomeScreenState
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock

class HomeScreenKtTest {

    @get:Rule
    val rule = createComposeRule()

    @Test
    fun homeScreen_displaysCorrectCocktail() {
        // Arrange
        val fakeRepository:CocktailRepository = mock()
        val viewModel = FakeHomeScreenViewModel(fakeRepository)
        val cocktail = Cocktail(idDrink = "1", strDrink = "Test Cocktail", strDrinkThumb = "url")
        viewModel._state.value = HomeScreenState(suggestionCocktail = cocktail, isLoading = false)

        // Act
        rule.setContent {
            HomeScreen(viewModel = viewModel, navigateTo = { /* mock navigation function */ })
        }

        // Assert
        rule.onNodeWithText("Test Cocktail").assertExists()
    }
}