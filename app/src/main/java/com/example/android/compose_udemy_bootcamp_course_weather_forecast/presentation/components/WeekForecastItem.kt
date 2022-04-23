package com.example.android.compose_udemy_bootcamp_course_weather_forecast.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.example.android.compose_udemy_bootcamp_course_weather_forecast.data.remote.dto.WeatherObject
import com.example.android.compose_udemy_bootcamp_course_weather_forecast.util.formatDate
import com.example.android.compose_udemy_bootcamp_course_weather_forecast.util.formatDecimals

@Composable
fun WeekForecastItem(data: WeatherObject) {
    val imageUrl = "http://openweathermap.org/img/wn/${data.weather[0].icon}.png"
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .clip(
                shape = RoundedCornerShape(
                    topStartPercent = 20,
                    bottomStartPercent = 20,
                    bottomEndPercent = 30
                )
            ),
        backgroundColor = MaterialTheme.colors.background
    ) {
        Row(
            modifier = Modifier.padding(2.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = formatDate(data.dt))
            WeatherStateImage(imageUrl = imageUrl)
            Text(
                text = data.weather[0].description,
                modifier = Modifier.background(Color(0xFFFFC400))
            )
            Text(text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Bold,
                        color = Color.Red
                    )
                ) {
                    append(text = formatDecimals(data.main.temp_max) + "°")
                }
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Light,
                        color = Color.Cyan
                    )
                ) {
                    append(text = formatDecimals(data.main.temp_min) + "°")
                }
            })
        }
    }
}