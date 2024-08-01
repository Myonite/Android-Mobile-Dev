package com.example.mixingstat.ui.common


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mixingstat.R
import com.example.mixingstat.presentation.viewmodel.CocktailDetailViewModel

@Composable
fun CocktailDetailScreen(
    id: String, navigateTo: (route: String) -> Unit,
    viewModel: CocktailDetailViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(id) {
        viewModel.searchById(id)
    }

    if (state.cocktail != null) {
        CocktailDetail(state.cocktail!!, navigateTo)
    } else {
        Text(
            text = stringResource(R.string.error_cocktail_not_found),
            modifier = Modifier.fillMaxSize(),
            textAlign = TextAlign.Center
        )
    }
}