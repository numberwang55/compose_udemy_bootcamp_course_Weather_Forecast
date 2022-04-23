package com.example.android.compose_udemy_bootcamp_course_weather_forecast.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.android.compose_udemy_bootcamp_course_weather_forecast.R
import com.example.android.compose_udemy_bootcamp_course_weather_forecast.data.remote.dto.WeatherDto
import com.example.android.compose_udemy_bootcamp_course_weather_forecast.util.formatDateTime

@Composable
fun SunriseSunsetRow(data: WeatherDto) {
    Row(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row() {
            Icon(
                painter = painterResource(id = R.drawable.sunrise),
                contentDescription = "Sunrise",
                modifier = Modifier.size(30.dp)
            )
            Text(
                text = formatDateTime(data.city.sunrise),
                style = MaterialTheme.typography.caption,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
        Row() {
            Text(
                text = formatDateTime(data.city.sunset),
                style = MaterialTheme.typography.caption,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
            Icon(
                painter = painterResource(id = R.drawable.sunset),
                contentDescription = "Sunset",
                modifier = Modifier.size(30.dp)
            )
        }
    }
}