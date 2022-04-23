package com.example.android.compose_udemy_bootcamp_course_weather_forecast.presentation.screens.about_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.example.android.compose_udemy_bootcamp_course_weather_forecast.R
import com.example.android.compose_udemy_bootcamp_course_weather_forecast.presentation.components.TopAppBarWeatherApp


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AboutScreen(navController: NavController) {
    Scaffold(topBar = {
        TopAppBarWeatherApp(
            title = "About",
            navController = navController,
            isMainScreen = false,
            icon = Icons.Default.ArrowBack,
            onButtonClick = { navController.popBackStack() },
        )
    }) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.app_version),
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.h6
            )
            Text(
                text = stringResource(id = R.string.api_info),
                fontWeight = FontWeight.Light,
                style = MaterialTheme.typography.subtitle1
            )
        }
    }
}