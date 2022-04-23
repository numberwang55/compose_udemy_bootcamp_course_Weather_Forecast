package com.example.android.compose_udemy_bootcamp_course_weather_forecast.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.android.compose_udemy_bootcamp_course_weather_forecast.data.local.entity.Favourite
import com.example.android.compose_udemy_bootcamp_course_weather_forecast.data.local.entity.UnitSettings

@Database(entities = [Favourite::class, UnitSettings::class], version = 1, exportSchema = true)
abstract class FavouriteDatabase: RoomDatabase() {

    abstract val dao: FavouriteDao
}