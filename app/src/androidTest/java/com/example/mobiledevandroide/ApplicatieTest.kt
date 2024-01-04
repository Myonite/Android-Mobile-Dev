package com.example.mobiledevandroide

import android.content.Context
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.hasText

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.Rule
import org.junit.jupiter.api.Test

class ApplicatieTest {

    @get:Rule
    val rule = createComposeRule()

    @BeforeEach
    fun setup() {
        ActivityScenario.launch(MainActivity::class.java)
    }

    @AfterEach
    fun tearDown() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val sharedPreferences = context.getSharedPreferences("Prefs", Context.MODE_PRIVATE)
        sharedPreferences.edit().clear().apply()
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun login_success() {
        rule.onNodeWithTag("Username").performTextInput("admin")
        rule.onNodeWithTag("Password").performTextInput("admin")
        rule.onNodeWithText("Inloggen").performClick()
        rule.waitUntilExactlyOneExists(hasText("HomeAdministration"), 10000L)
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun login_failure() {
        rule.onNodeWithTag("Username").performTextInput("faulty")
        rule.onNodeWithTag("Password").performTextInput("faulty")
        rule.onNodeWithText("Inloggen").performClick()
        rule.waitUntilExactlyOneExists(hasText("Inloggen"), 10000L)
    }


    @Test
    fun addReceipt_success() {

    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun navigateToHomeScreen() {
        rule.onNodeWithTag("Username").performTextInput("admin")
        rule.onNodeWithTag("Password").performTextInput("admin")
        rule.onNodeWithText("Inloggen").performClick()
        rule.waitUntilExactlyOneExists(hasText("HomeAdministration"), 10000L)
        rule.onNodeWithTag("About").performClick()
        rule.onNodeWithText("MobileDevAndroid").assertExists()
        rule.onNodeWithTag("Home").performClick()
        rule.onNodeWithText("HomeAdministration").assertExists()
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun navigateToAboutScreen() {
        rule.onNodeWithTag("Username").performTextInput("admin")
        rule.onNodeWithTag("Password").performTextInput("admin")
        rule.onNodeWithText("Inloggen").performClick()
        rule.waitUntilExactlyOneExists(hasText("HomeAdministration"), 10000L)

        rule.onNodeWithTag("About").performClick()
        rule.onNodeWithText("MobileDevAndroid").assertExists()
    }


}
