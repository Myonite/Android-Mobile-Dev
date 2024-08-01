package com.example.mixingstat.ui.screen.detail

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.mixingstat.R
import com.example.mixingstat.data.models.Cocktail

@Composable
fun InstructionsSection(cocktail: Cocktail) {
    Text(
        text = stringResource(R.string.instructions),
        style = MaterialTheme.typography.headlineSmall,
        modifier = Modifier.padding(top = 16.dp)
    )
    Text(
        text = "${cocktail.strInstructions}",
        style = MaterialTheme.typography.bodyLarge,
        color = MaterialTheme.colorScheme.onSurface,
        modifier = Modifier.padding(top = 10.dp)
    )
}