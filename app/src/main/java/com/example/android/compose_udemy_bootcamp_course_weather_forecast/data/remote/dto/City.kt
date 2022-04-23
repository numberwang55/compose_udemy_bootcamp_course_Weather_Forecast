package com.example.android.compose_udemy_bootcamp_course_weather_forecast.data.remote.dto

data class City(
    val coord: Coord,
    val country: String,
    val id: Int,
    val name: String,
    val population: Int,
    val sunrise: Int,
    val sunset: Int,
    val timezone: Int
)