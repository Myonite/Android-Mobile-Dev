package com.example.mobiledevandroide.ui.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mobiledevandroide.utils.Direction
import com.example.mobiledevandroide.ui.components.autth.LoginButton
import com.example.mobiledevandroide.ui.components.autth.PasswordTextField
import com.example.mobiledevandroide.ui.components.autth.UsernameTextField
import com.example.mobiledevandroide.viewModels.LoginResult
import com.example.mobiledevandroide.viewModels.LoginViewModel


@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel = viewModel(),
    navigateTo: (route: String) -> Unit,
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isLoading: Boolean by remember { mutableStateOf(false) }

    val loginResult by loginViewModel.loginResult.observeAsState()


    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 30.dp)
    ) {
        Text("Login", style = TextStyle(fontSize = 30.sp))
        UsernameTextField(username = username, onValueChange = { username = it })
        PasswordTextField(
            password = password,
            onValueChange = { password = it },
        )
        LoginButton(
            onClick = {
                isLoading = true
                loginViewModel.login(username, password)

            },
            isLoading = isLoading,
        )
        if (isLoading) {
            loginResult?.let {
                when (it) {
                    is LoginResult.Success -> {
                        isLoading = false
                        navigateTo(Direction.Home.route)
                    }

                    is LoginResult.Error -> {
                        isLoading = false
                    }
                }
            }
        }

    }
}
