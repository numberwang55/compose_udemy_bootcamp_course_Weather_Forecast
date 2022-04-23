package com.example.android.compose_udemy_bootcamp_course_weather_forecast.presentation.screens.splash_screen

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.android.compose_udemy_bootcamp_course_weather_forecast.R
import com.example.android.compose_udemy_bootcamp_course_weather_forecast.common.DataOrException
import com.example.android.compose_udemy_bootcamp_course_weather_forecast.data.remote.dto.WeatherDto
import com.example.android.compose_udemy_bootcamp_course_weather_forecast.presentation.screens.favourites_screen.FavouriteScreenViewModel
import com.example.android.compose_udemy_bootcamp_course_weather_forecast.presentation.screens.main_screen.MainScreenViewModel
import com.example.android.compose_udemy_bootcamp_course_weather_forecast.util.Routes
import kotlinx.coroutines.delay

@Composable
fun WeatherSplashScreen(
    favouriteScreenViewModel: FavouriteScreenViewModel = hiltViewModel(),
    mainScreenViewModel: MainScreenViewModel = hiltViewModel(),
    navController: NavController
) {
    var defaultCity = "Exbourne"
    val viewModelListOfFavourites =
        favouriteScreenViewModel.listOfFavourites.collectAsState(initial = emptyList()).value
    if (viewModelListOfFavourites.isNotEmpty()) {
        defaultCity = viewModelListOfFavourites[0].city
    }
    val weatherData =
        produceState<DataOrException<WeatherDto, Boolean, Exception>>(
            initialValue = DataOrException(isLoading = true)
        ) {
            value = mainScreenViewModel.getWeatherData(
                city = defaultCity,
                units = "metric"
            )
        }.value
    val scale = remember { Animatable(0f) }
    LaunchedEffect(key1 = defaultCity, key2 = weatherData, block = {
        scale.animateTo(
            targetValue = 0.9f,
            animationSpec = tween(
                900,
                easing = {
                    OvershootInterpolator(9f).getInterpolation(it)
                })
        )
        delay(2000L)
        if (weatherData.data == null){
            navController.navigate(Routes.NO_DATA_SCREEN)
        } else
            navController.navigate(Routes.MAIN_SCREEN + "?city=${defaultCity}")
    })
    Surface(
        modifier = Modifier
            .padding(16.dp)
            .size(330.dp)
            .scale(scale = scale.value),
        shape = CircleShape,
        color = Color.White,
        border = BorderStroke(width = 2.dp, color = Color.LightGray)
    ) {
        Column(
            modifier = Modifier.padding(1.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.sun),
                contentDescription = "Sun",
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(100.dp)
            )
            Text(
                text = "Find the sun",
                style = MaterialTheme.typography.h5,
                color = Color.LightGray
            )
        }
    }
}