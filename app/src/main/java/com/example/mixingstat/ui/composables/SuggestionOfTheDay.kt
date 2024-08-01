package com.example.mixingstat.ui.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.mixingstat.R
import com.example.mixingstat.models.Cocktail
import com.example.mixingstat.utils.StringUtils


@Composable
fun SuggestionOfTheDay(cocktail: Cocktail, navigateTo: (route: String) -> Unit) {
    Text(
        text = stringResource(R.string.title_suggestion_of_the_moment),
        style = MaterialTheme.typography.titleLarge,
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth()
    )
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { navigateTo("cocktail/${cocktail.idDrink}") },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
        ) {
            AsyncImage(
                model = cocktail.strDrinkThumb,
                contentDescription = cocktail.strDrink,
                modifier = Modifier
                    .height(160.dp)
                    .width(160.dp)
            )
            Column(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .align(Alignment.CenterVertically)
            ) {
                Text(text = cocktail.strDrink, style = MaterialTheme.typography.displaySmall)
                Text(text = stringResource(R.string.category) + StringUtils.defaultIfNull(cocktail.strCategory))
                Text(text = stringResource(R.string.alcoholic) + StringUtils.defaultIfNull(cocktail.strAlcoholic))
                Text(text = stringResource(R.string.glass) + StringUtils.defaultIfNull(cocktail.strGlass))
            }
        }
    }
}