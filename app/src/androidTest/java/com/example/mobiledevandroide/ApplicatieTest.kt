package com.example.mobiledevandroide

import androidx.compose.ui.test.performClick

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import androidx.test.core.app.ActivityScenario
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ApplicatieTest {

    @get:Rule
    val rule = createComposeRule()
    @Before
    fun setup() {
        ActivityScenario.launch(MainActivity::class.java)
    }
    @Test
    fun openApp() {
        rule.onNodeWithText("Login").assertExists()
    }


    @Test
    fun login_success(){
        rule.onNodeWithTag("Username").performTextInput("admin")
        rule.onNodeWithTag("Password").performTextInput("admin")
        rule.onNodeWithText("Login").performClick()


    }
    @Test
    fun navigateToHomeScreen() {
        rule.onNodeWithText("Login").performClick()

        rule.onNodeWithText("Home").assertExists()
    }

    @Test
    fun navigateToAboutScreen() {
        rule.onNodeWithText("Login").performClick()

        rule.onNodeWithText("About").assertExists()
    }

}
