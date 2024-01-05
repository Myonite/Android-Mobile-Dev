package com.example.mobiledevandroide.ui.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mobiledevandroide.ui.components.auth.LoginButton
import com.example.mobiledevandroide.ui.components.auth.PasswordTextField
import com.example.mobiledevandroide.ui.components.auth.UsernameTextField
import com.example.mobiledevandroide.utils.Direction
import com.example.mobiledevandroide.viewModels.LoginResult
import com.example.mobiledevandroide.viewModels.LoginViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel = viewModel(),
    navigateTo: (route: String) -> Unit,
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

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
                loginViewModel.login(username, password) { message ->

                    coroutineScope.launch {
                        snackbarHostState.showSnackbar(message)
                        delay(5000)
                        isLoading = false
                    }
                }
            },
            isLoading = isLoading,
        )
        loginResult?.let {
            when (it) {
                is LoginResult.Success -> {
                    coroutineScope.launch {
                        navigateTo(Direction.Home.route)
                    }
                }

                else -> {/*This needs to be decided. On fail this should do nothing*/}
            }
        }
    }

    SnackbarHost(
        hostState = snackbarHostState,
        snackbar = {
            Snackbar(
                modifier = Modifier.padding(16.dp),
                action = {
                    TextButton(onClick = { snackbarHostState.currentSnackbarData?.performAction() }) {
                        Text("Dismiss")
                    }
                }
            ) {
                Text(snackbarHostState.currentSnackbarData?.visuals!!.message)
            }
        }
    )
}
