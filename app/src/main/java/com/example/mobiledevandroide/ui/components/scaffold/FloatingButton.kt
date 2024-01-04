package com.example.mobiledevandroide.ui.components.scaffold

import android.net.Uri
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun FloatingButton(fileChooser: ManagedActivityResultLauncher<String, Uri?>) {
    FloatingActionButton(
        onClick = {
            fileChooser.launch("image/*")
        },
        modifier = Modifier.padding(16.dp),
    ) {
        Icon(imageVector = Icons.Default.Add, contentDescription = null)
    }
}
