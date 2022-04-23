package com.example.android.compose_udemy_bootcamp_course_weather_forecast.presentation.screens.no_data_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.android.compose_udemy_bootcamp_course_weather_forecast.common.DataOrException
import com.example.android.compose_udemy_bootcamp_course_weather_forecast.data.remote.dto.WeatherDto
import com.example.android.compose_udemy_bootcamp_course_weather_forecast.presentation.screens.main_screen.MainScreenViewModel
import com.example.android.compose_udemy_bootcamp_course_weather_forecast.util.Routes
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun NoDataScreen(
    viewModel: MainScreenViewModel = hiltViewModel(),
    navController: NavController
) {
    val coroutineScope = rememberCoroutineScope()
    val defaultCity = "Okehampton"
    val weatherDataState: MutableState<DataOrException<WeatherDto, Boolean, Exception>> = remember { mutableStateOf(
        DataOrException()
    ) }
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
        LaunchedEffect(viewModel.isRefreshing) {
            if (viewModel.isRefreshing) {
                delay(2000L)
                viewModel.isRefreshing = false
            }
        }
        SwipeRefresh(
            state = rememberSwipeRefreshState(
                isRefreshing = viewModel.isRefreshing
            ),
            onRefresh = {
                coroutineScope.launch {
                    weatherDataState.value = viewModel.getWeatherData("London", "metric")
                }
            }
        ) {
            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                item {
                    Text(
                        text = "Connection error - please check your internet connection and restart",
                        textAlign = TextAlign.Center
                    )
                    if (weatherDataState.value.data != null) {
                        navController.navigate(Routes.MAIN_SCREEN + "?city=$defaultCity")
                    }
                }
            }
        }
    }
}