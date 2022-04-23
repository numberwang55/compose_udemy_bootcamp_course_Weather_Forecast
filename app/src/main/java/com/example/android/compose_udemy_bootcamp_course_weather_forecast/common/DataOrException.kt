package com.example.android.compose_udemy_bootcamp_course_weather_forecast.common

data class DataOrException<T, Boolean, E: Exception>(
    var data: T? = null,
    var isLoading: Boolean? = null,
    var e: E? = null
)