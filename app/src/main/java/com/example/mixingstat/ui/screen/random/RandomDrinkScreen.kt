package com.example.mixingstat.ui.screen.random

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.QuestionMark
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mixingstat.presentation.viewmodel.random.RandomDrinkViewModel
import com.example.mixingstat.ui.screen.detail.CocktailDetailScreen
import kotlinx.coroutines.delay

@Composable
fun RandomDrinkScreen(
    navigateTo: (route: String) -> Unit,
    viewModel: RandomDrinkViewModel = hiltViewModel()
) {
    val randomDrinkState by viewModel.randomDrinkState.collectAsState()

    val infiniteTransition = rememberInfiniteTransition(label = "random")
    val angle by infiniteTransition.animateFloat(
        initialValue = -10f,
        targetValue = 10f,
        animationSpec = infiniteRepeatable(
            animation = tween(350, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ), label = "random"
    )
    var targetAlpha by remember { mutableFloatStateOf(1f) }
    var showCocktail by remember { mutableStateOf(false) }
    val alpha by animateFloatAsState(
        targetValue = targetAlpha,
        animationSpec = tween(400), label = "random"
    )

    LaunchedEffect(key1 = randomDrinkState.showCocktail) {
        if (randomDrinkState.showCocktail) {
            delay(1000)
            targetAlpha = 0f
            showCocktail = true
        }
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        if (!showCocktail) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Icon(
                    imageVector = Icons.Default.QuestionMark,
                    contentDescription = "Question Icon",
                    modifier = Modifier
                        .graphicsLayer(
                            rotationZ = angle,
                            alpha = alpha
                        )
                        .size(200.dp),
                    tint = MaterialTheme.colorScheme.primaryContainer
                )
            }
        } else {
            randomDrinkState.selectedCocktail?.let {
                CocktailDetailScreen(it.idDrink, navigateTo)
            }
        }
    }
}