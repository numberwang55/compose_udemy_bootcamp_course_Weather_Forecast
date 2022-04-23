package com.example.android.compose_udemy_bootcamp_course_weather_forecast.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
fun WeatherDetailRow(data: WeatherObject) {
    val imageUrl = "http://openweathermap.org/img/wn/${data.weather[0].icon}.png"

    Surface(
        modifier = Modifier
            .padding(3.dp)
            .fillMaxWidth(),
        shape = CircleShape.copy(topEnd = CornerSize(6.dp)),
        color = Color.White
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.padding(start = 15.dp)) {
                Text(
                    text = formatDate(data.dt).split(",")[0] + " ",
                )
                Text(
                    text = data.dt_txt.split("\\s".toRegex())[1].substringBeforeLast(":"),
                )
            }
            WeatherStateImage(imageUrl = imageUrl)
            Surface(shape = CircleShape, color = Color(0xFFFFC400))
            {
                Text(
                    text = data.weather[0].description,
                    modifier = Modifier.padding(4.dp),
                    style = MaterialTheme.typography.caption
                )
            }
            Text(text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Blue.copy(alpha = 0.7f)
                    )
                ) {
                    append(text = formatDecimals(data.main.temp_max) + "°")
                }
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Light,
                        color = Color.LightGray
                    )
                ) {
                    append(text = formatDecimals(data.main.temp_min) + "°")
                }
            }, modifier = Modifier.padding(end = 15.dp))
        }
    }
}