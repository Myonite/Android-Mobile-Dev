package com.example.mixingstat.ui.screen.detail

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.mixingstat.data.models.Cocktail

/**
 * Composable function that displays the header of a cocktail.
 *
 * @param cocktail The cocktail whose header is to be displayed.
 */
@Composable
fun CocktailHeader(cocktail: Cocktail) {
    // Create a Box that fills the maximum width of the parent
    Box(modifier = Modifier.fillMaxWidth()) {
        // Create a Column that is centered in the Box
        Column(
            modifier = Modifier.align(Alignment.Center)
        ) {
            // Display the name of the cocktail in a Text component
            Text(
                text = cocktail.strDrink,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier
                    .padding(bottom = 10.dp) // Apply bottom padding of 10dp
                    .align(Alignment.CenterHorizontally) // Center the Text horizontally
            )
            // Display the image of the cocktail in an AsyncImage component
            AsyncImage(
                model = cocktail.strDrinkThumb,
                contentDescription = cocktail.strDrink,
                modifier = Modifier
                    .height(300.dp) // Set the height of the image to 300dp
                    .clip(RoundedCornerShape(16.dp)) // Clip the image with rounded corners
                    .border(BorderStroke(2.dp, Color.Gray)) // Add a border to the image
                    .shadow(elevation = 4.dp) // Add a shadow to the image
            )
        }
    }
}