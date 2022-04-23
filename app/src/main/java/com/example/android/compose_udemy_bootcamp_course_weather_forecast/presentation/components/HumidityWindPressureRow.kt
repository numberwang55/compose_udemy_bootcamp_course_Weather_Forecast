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
import com.example.android.compose_udemy_bootcamp_course_weather_forecast.data.remote.dto.WeatherObject

@Composable
fun HumidityWindPressureRow(data: WeatherObject, isImperial: Boolean) {
    Row(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row {
            Icon(
                painter = painterResource(id = R.drawable.humidity),
                contentDescription = "humidity",
                modifier = Modifier.size(20.dp)
            )
            Text(
                text = "  ${data.main.humidity}% humidity",
                style = MaterialTheme.typography.caption
            )
        }
        Row() {
            Icon(
                painter = painterResource(id = R.drawable.pressure),
                contentDescription = "pressure",
                modifier = Modifier.size(20.dp)
            )
            Text(text = " ${data.main.pressure} psi", style = MaterialTheme.typography.caption)
        }
        Row() {
            Icon(
                painter = painterResource(id = R.drawable.wind),
                contentDescription = "wind",
                modifier = Modifier.size(20.dp)
            )
            Text(
                text = " ${data.wind.speed} " + if (isImperial) "mph" else "m/s",
                style = MaterialTheme.typography.caption
            )
        }
    }
}