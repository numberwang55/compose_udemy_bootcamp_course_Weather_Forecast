package com.example.android.compose_udemy_bootcamp_course_weather_forecast.data.remote.dto

data class Weather(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)