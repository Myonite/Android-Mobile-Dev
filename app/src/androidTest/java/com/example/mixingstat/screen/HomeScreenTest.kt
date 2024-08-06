package com.example.mixingstat.screen

import androidx.activity.viewModels
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.example.mixingstat.MainActivity
import com.example.mixingstat.presentation.viewmodel.home.HomeScreenViewModel
import com.example.mixingstat.ui.screen.home.HomeScreen
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
@HiltAndroidTest
class HomeScreenTest {

    @get:Rule(order = 1)
    var hiltTestRule = HiltAndroidRule(this)

    @get:Rule(order = 2)
    var composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setup() {
        hiltTestRule.inject()
        composeTestRule.setContent {
            HomeScreen(navigateTo = {}, viewModel = composeTestRule.activity.viewModels<HomeScreenViewModel>().value)
        }
    }

    @Test
    fun whenLoading_showLoadingIndicator() {
        composeTestRule.onNodeWithTag("LoadingIndicator").assertIsDisplayed()
    }
}