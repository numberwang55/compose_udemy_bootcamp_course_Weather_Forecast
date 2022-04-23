package com.example.android.compose_udemy_bootcamp_course_weather_forecast.data.remote.dto

data class WeatherObject(
    val clouds: Clouds,
    val dt: Int,
    val dt_txt: String,
    val main: Main,
    val pop: Double,
    val rain: Rain,
    val sys: Sys,
    val visibility: Int,
    val weather: List<Weather>,
    val wind: Wind
)