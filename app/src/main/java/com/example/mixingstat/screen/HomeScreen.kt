package com.example.mixingstat.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.mixingstat.composables.ImageSlider
import com.example.mixingstat.composables.SuggestionOfTheDay
import com.example.mixingstat.dev_seeding.cokatilList

@Composable
fun HomeScreen(navigateTo: (route: String) -> Unit) {
    Row(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            ImageSlider(cokatilList, navigateTo, text = "Most popular cocktails")
            ImageSlider(cokatilList,navigateTo, text = "Latest cocktails")
            SuggestionOfTheDay(cokatilList[0]) {

            }
        }
    }
}