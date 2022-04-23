package com.example.android.compose_udemy_bootcamp_course_weather_forecast.data.local.entity

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Favourite(
    @NonNull @PrimaryKey val city: String,
    val country: String
)
