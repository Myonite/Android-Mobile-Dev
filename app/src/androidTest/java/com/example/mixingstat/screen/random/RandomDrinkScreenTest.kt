package com.example.mixingstat.screen.random

import androidx.activity.compose.setContent
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.example.mixingstat.MainActivity
import com.example.mixingstat.presentation.state.random.RandomDrinkState
import com.example.mixingstat.presentation.viewmodel.random.RandomDrinkViewModel
import com.example.mixingstat.ui.screen.random.RandomDrinkScreen
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
class RandomDrinkScreenTest {

    @get:Rule(order = 1)
    var hiltTestRule = HiltAndroidRule(this)

    @get:Rule(order = 2)
    var composeTestRule = createAndroidComposeRule<MainActivity>()

    private val stateFlow = MutableStateFlow(
        RandomDrinkState(
            selectedCocktail = null,
            showCocktail = false
        )
    )

    private val mockViewModel: RandomDrinkViewModel = mockk(relaxed = true)

    @Before
    fun setup() {
        hiltTestRule.inject()

        every { mockViewModel.randomDrinkState } returns stateFlow

        composeTestRule.activity.setContent {
            RandomDrinkScreen(navigateTo = {}, viewModel = mockViewModel)
        }
    }

    @Test
    fun whenNoCocktail_showQuestionIcon() {
        composeTestRule.onNodeWithTag("QuestionIcon").assertIsDisplayed()
    }

}