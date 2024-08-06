package com.example.mixingstat.screen.home

import androidx.activity.compose.setContent
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.printToLog
import com.example.mixingstat.MainActivity
import com.example.mixingstat.R
import com.example.mixingstat.data.models.Cocktail
import com.example.mixingstat.presentation.state.home.HomeScreenState
import com.example.mixingstat.presentation.viewmodel.home.HomeScreenViewModel
import com.example.mixingstat.ui.screen.home.HomeScreen
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
@HiltAndroidTest
class HomeScreenTest {

    private var cocktailList = listOf(
        Cocktail(
            idDrink = "1",
            strDrink = "Test Cocktail",
            strDrinkThumb = "https://www.thecocktaildb.com/images/media/drink/metwgh1606770327.jpg"
        ),
        Cocktail(
            idDrink = "2",
            strDrink = "Test Cocktail",
            strDrinkThumb = "https://www.thecocktaildb.com/images/media/drink/metwgh1606770327.jpg"
        ),
        Cocktail(
            idDrink = "3",
            strDrink = "Test Cocktail",
            strDrinkThumb = "https://www.thecocktaildb.com/images/media/drink/metwgh1606770327.jpg"
        ),
        Cocktail(
            idDrink = "4",
            strDrink = "Test Cocktail",
            strDrinkThumb = "https://www.thecocktaildb.com/images/media/drink/metwgh1606770327.jpg"
        ),
        Cocktail(
            idDrink = "5",
            strDrink = "Test Cocktail",
            strDrinkThumb = "https://www.thecocktaildb.com/images/media/drink/metwgh1606770327.jpg"
        ),
        Cocktail(
            idDrink = "6",
            strDrink = "Test Cocktail",
            strDrinkThumb = "https://www.thecocktaildb.com/images/media/drink/metwgh1606770327.jpg"
        ))

    @get:Rule(order = 1)
    var hiltTestRule = HiltAndroidRule(this)

    @get:Rule(order = 2)
    var composeTestRule = createAndroidComposeRule<MainActivity>()

    private val stateFlow = MutableStateFlow(
        HomeScreenState(
            isLoading = false,
            popularCocktails = cocktailList,
            latestCocktails = cocktailList,
            suggestionCocktail = cocktailList[0]
        )
    )

    private val mockViewModel: HomeScreenViewModel = mockk(relaxed = true)

    @Before
    fun setup() {
        hiltTestRule.inject()

        every { mockViewModel.state } returns stateFlow

        composeTestRule.activity.setContent {
            HomeScreen(navigateTo = {}, viewModel = mockViewModel)
        }
    }

    @Test
    fun whenLoading_showLoadingIndicator() {
        stateFlow.value = HomeScreenState(isLoading = true)
        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithTag("LoadingIndicator").assertIsDisplayed()
    }

    @Test
    fun whenNotLoading_showCocktail() {
        composeTestRule.onNodeWithTag("SuggestionCard1").assertIsDisplayed()
    }

    @Test

    fun whenNoCocktails_showErrorMessage() {
        stateFlow.value = HomeScreenState(
            isLoading = false,
            popularCocktails = emptyList(),
            latestCocktails = emptyList(),
            suggestionCocktail = null
        )
        composeTestRule.waitForIdle()
        composeTestRule.onRoot().printToLog("currentLabelExsists")
        composeTestRule.onNodeWithText( composeTestRule.activity.getString(R.string.possible_network_error)).assertIsDisplayed()
    }

}