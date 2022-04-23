package com.example.android.compose_udemy_bootcamp_course_weather_forecast.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.android.compose_udemy_bootcamp_course_weather_forecast.presentation.components.TopAppBarWeatherApp
import com.example.android.compose_udemy_bootcamp_course_weather_forecast.presentation.ui.theme.Compose_udemy_bootcamp_course_Weather_ForecastTheme
import com.example.android.compose_udemy_bootcamp_course_weather_forecast.util.WeatherNavigation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
                val navController = rememberNavController()
                WeatherNavigation(navHostController = navController)
//                TopAppBarWeatherApp(title = "", navController = navController)
            }
        }
    }
}

@Composable
fun MyApp(content: @Composable () -> Unit) {
    Compose_udemy_bootcamp_course_Weather_ForecastTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                content()
            }
        }
    }
}