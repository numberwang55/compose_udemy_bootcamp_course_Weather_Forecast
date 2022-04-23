package com.example.android.compose_udemy_bootcamp_course_weather_forecast.presentation.components

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun WeatherStateImage(imageUrl: String) {
    AsyncImage(
        model = imageUrl,
        contentDescription = "Weather Image",
        contentScale = ContentScale.Fit,
        modifier = Modifier.size(90.dp)
    )
}