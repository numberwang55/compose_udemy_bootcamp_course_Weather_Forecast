package com.example.android.compose_udemy_bootcamp_course_weather_forecast.data.remote.dto

data class WeatherDto(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<WeatherObject>,
    val message: Int
)