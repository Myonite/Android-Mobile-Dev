package com.example.mixingstat.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.mixingstat.R
import com.example.mixingstat.ui.composables.ImageSlider
import com.example.mixingstat.ui.composables.SuggestionOfTheDay
import com.example.mixingstat.dev_seeding.cokatilList

@Composable
fun HomeScreen(navigateTo: (route: String) -> Unit) {
    Row(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            ImageSlider(cokatilList, navigateTo, text = stringResource(R.string.title_popular_cocktails))
            ImageSlider(cokatilList,navigateTo, text = stringResource(R.string.title_latest_cocktails))
            SuggestionOfTheDay(cokatilList[0]) {

            }
        }
    }
}