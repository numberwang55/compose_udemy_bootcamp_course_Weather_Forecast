package com.example.android.compose_udemy_bootcamp_course_weather_forecast.data.remote

import com.example.android.compose_udemy_bootcamp_course_weather_forecast.common.Constants
import com.example.android.compose_udemy_bootcamp_course_weather_forecast.data.remote.dto.WeatherDto
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("data/2.5/forecast")
    suspend fun getWeather(
        @Query("q") query: String,
        @Query("units") units: String = "metric",
        @Query("appid") appId: String = Constants.API_KEY
    ): WeatherDto
}