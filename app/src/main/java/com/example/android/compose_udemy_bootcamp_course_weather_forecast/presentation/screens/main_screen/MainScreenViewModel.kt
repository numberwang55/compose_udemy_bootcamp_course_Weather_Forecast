package com.example.android.compose_udemy_bootcamp_course_weather_forecast.presentation.screens.main_screen

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.compose_udemy_bootcamp_course_weather_forecast.common.DataOrException
import com.example.android.compose_udemy_bootcamp_course_weather_forecast.data.remote.dto.WeatherDto
import com.example.android.compose_udemy_bootcamp_course_weather_forecast.domain.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository
): ViewModel() {

    var isRefreshing by mutableStateOf(false)

    suspend fun getWeatherData(city: String, units: String = "metric"): DataOrException<WeatherDto, Boolean, Exception> {
        return weatherRepository.getWeather(cityQuery = city, units = units)
    }

}