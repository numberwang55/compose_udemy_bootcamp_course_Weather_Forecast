package com.example.android.compose_udemy_bootcamp_course_weather_forecast.domain.repository

import androidx.room.*
import com.example.android.compose_udemy_bootcamp_course_weather_forecast.common.DataOrException
import com.example.android.compose_udemy_bootcamp_course_weather_forecast.data.local.entity.Favourite
import com.example.android.compose_udemy_bootcamp_course_weather_forecast.data.local.entity.UnitSettings
import com.example.android.compose_udemy_bootcamp_course_weather_forecast.data.remote.dto.WeatherDto
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {

    suspend fun getWeather(cityQuery: String, units: String): DataOrException<WeatherDto, Boolean, Exception>

    fun getFavourites(): Flow<List<Favourite>>

    suspend fun getFavById(city: String): Favourite

    suspend fun insertFavourite(favourite: Favourite)

    suspend fun deleteFavourite(favourite: Favourite)

    suspend fun updateFavourite(favourite: Favourite)

    suspend fun deleteAllFavourites()

    // UnitSettings
    fun getUnitSettings(): Flow<List<UnitSettings>>

    suspend fun insertUnitSetting(unitSettings: UnitSettings)

    suspend fun updateUnitSetting(unitSettings: UnitSettings)

    suspend fun deleteAllUnitSettings()

    suspend fun deleteUnitSetting(unitSettings: UnitSettings)

}