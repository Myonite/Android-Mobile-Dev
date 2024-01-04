package com.example.mobiledevandroide.ui.components.auth

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun PasswordTextField(password: String, onValueChange: (String) -> Unit) {
    OutlinedTextField(
        value = password,
        onValueChange = onValueChange,
        label = { Text("Wachtwoord") },
        visualTransformation = PasswordVisualTransformation(),
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
            .testTag("Password"),
    )
}