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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.QuestionMark
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mixingstat.R
import com.example.mixingstat.presentation.viewmodel.random.RandomDrinkViewModel
import com.example.mixingstat.ui.screen.detail.CocktailDetailScreen
import kotlinx.coroutines.delay
/**
 * Composable function that displays a random drink screen.
 *
 * This function displays a question mark icon that rotates back and forth while the random drink is being loaded.
 * Once the random drink is loaded, it displays the details of the drink using the CocktailDetailScreen composable.
 * If the random drink cannot be loaded, it displays an error message.
 *
 * @param navigateTo A function that takes a route as a parameter and navigates to the corresponding screen.
 * @param viewModel The ViewModel that provides the state for the RandomDrinkScreen. By default, it uses the RandomDrinkViewModel provided by Hilt.
 */
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
    val targetAlpha by remember { mutableFloatStateOf(1f) }
    var showCocktail by remember { mutableStateOf(false) }
    val alpha by animateFloatAsState(
        targetValue = targetAlpha,
        animationSpec = tween(400), label = "random"
    )

    LaunchedEffect(Unit) {
        delay(1000)
        showCocktail = randomDrinkState.selectedCocktail != null
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
                        .size(200.dp)
                        .semantics { testTag = "QuestionIcon" },
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        } else {
            if (randomDrinkState.selectedCocktail == null) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(
                        stringResource(R.string.possible_network_error),
                        style = MaterialTheme.typography.titleLarge,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            } else {
                randomDrinkState.selectedCocktail?.let {
                    CocktailDetailScreen(it.idDrink, navigateTo)
                }
            }
        }
    }
}