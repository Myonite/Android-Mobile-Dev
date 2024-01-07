package com.example.mobiledevandroide.ui.components.auth

import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun LoginButton(onClick: () -> Unit, isLoading: Boolean) {
    Button(
        onClick = {
            try {
                Log.d("LoginButton", "Button clicked")
                onClick()
            } catch (e: Exception) {
                Log.e("LoginButton", "Error in onClick", e)
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        enabled = !isLoading,
    ) {
        if (isLoading) {
            CircularProgressIndicator(color = Color.White)
        } else {
            Text("Inloggen")
        }
    }
}