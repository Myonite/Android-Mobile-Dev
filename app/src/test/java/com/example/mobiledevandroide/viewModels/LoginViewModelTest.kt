package com.example.mobiledevandroide.viewModels



import android.app.Application
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(application = Application::class)
class LoginViewModelTest {


    private lateinit var viewModel: LoginViewModel

    @Before
    fun setUp() {
        val application = RuntimeEnvironment.getApplication()
        viewModel = LoginViewModel(application)
    }

    @Test
    fun testLoginSuccess() {
        viewModel.login("admin", "admin")

        val result = viewModel.loginResult.value
        assertTrue(result is LoginResult.Success)
        assertEquals("Success", (result as LoginResult.Success).message)
    }

    @Test
    fun testLoginError() {
        viewModel.login("ongeldigeGebruiker", "ongeldigWachtwoord")

        val result = viewModel.loginResult.value
        assertTrue(result is LoginResult.Error)
        assertEquals("Error", (result as LoginResult.Error).message)
    }
}