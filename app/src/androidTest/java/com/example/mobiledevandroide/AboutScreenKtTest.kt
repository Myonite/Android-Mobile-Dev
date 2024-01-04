package com.example.mobiledevandroide

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import com.example.mobiledevandroide.ui.views.AboutScreen
import org.junit.Rule
import org.junit.Test

class AboutScreenTest {

    @get:Rule
    val rule = createComposeRule()

    @Test
    fun aboutScreen_displaysCorrectText() {
        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext()
        )
        rule.setContent {
            AboutScreen(navController = navController)
        }

        rule.onNodeWithText("MobileDevAndroid").assertExists()
        rule.onNodeWithText("Version 1.0").assertExists()
        rule.onNodeWithText("An application based on a project for webservices, some parts have been reduced to allow for a more fluent use on a mobile device.")
            .assertExists()
    }
}
