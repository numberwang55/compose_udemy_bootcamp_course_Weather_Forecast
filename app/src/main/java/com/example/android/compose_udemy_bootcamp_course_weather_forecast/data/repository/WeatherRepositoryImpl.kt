package com.example.android.compose_udemy_bootcamp_course_weather_forecast.data.repository

import android.util.Log
import com.example.android.compose_udemy_bootcamp_course_weather_forecast.common.DataOrException
import com.example.android.compose_udemy_bootcamp_course_weather_forecast.data.local.FavouriteDao
import com.example.android.compose_udemy_bootcamp_course_weather_forecast.data.local.entity.Favourite
import com.example.android.compose_udemy_bootcamp_course_weather_forecast.data.local.entity.UnitSettings
import com.example.android.compose_udemy_bootcamp_course_weather_forecast.data.remote.WeatherApi
import com.example.android.compose_udemy_bootcamp_course_weather_forecast.data.remote.dto.WeatherDto
import com.example.android.compose_udemy_bootcamp_course_weather_forecast.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import kotlin.Exception

class WeatherRepositoryImpl @Inject constructor(
    private val api: WeatherApi,
    private val dao: FavouriteDao
): WeatherRepository {

    override suspend fun getWeather(cityQuery: String, units: String)
            :DataOrException<WeatherDto, Boolean, Exception>  {
        val response = try {
            api.getWeather(query = cityQuery, units = units)

        }catch (e: Exception){
            Log.d("REX", "getWeather: $e")
            return DataOrException(e = e)
        }
        Log.d("INSIDE", "getWeather: $response")
        return  DataOrException(data = response)
    }

    override fun getFavourites(): Flow<List<Favourite>> {
        return dao.getFavourites()
    }

    override suspend fun getFavById(city: String): Favourite {
        return dao.getFavById(city)
    }

    override suspend fun insertFavourite(favourite: Favourite) {
        dao.insertFavourite(favourite)
    }

    override suspend fun deleteFavourite(favourite: Favourite) {
        dao.deleteFavourite(favourite)
    }

    override suspend fun updateFavourite(favourite: Favourite) {
        dao.updateFavourite(favourite)
    }

    override suspend fun deleteAllFavourites() {
        dao.deleteAllFavourites()
    }


    // UnitSettings
    override fun getUnitSettings(): Flow<List<UnitSettings>> {
        return dao.getUnitSettings()
    }

    override suspend fun insertUnitSetting(unitSettings: UnitSettings) {
        dao.insertUnitSetting(unitSettings)
    }

    override suspend fun updateUnitSetting(unitSettings: UnitSettings) {
        dao.updateUnitSetting(unitSettings)
    }

    override suspend fun deleteAllUnitSettings() {
        dao.deleteAllUnitSettings()
    }

    override suspend fun deleteUnitSetting(unitSettings: UnitSettings) {
        dao.deleteUnitSetting(unitSettings)
    }
}