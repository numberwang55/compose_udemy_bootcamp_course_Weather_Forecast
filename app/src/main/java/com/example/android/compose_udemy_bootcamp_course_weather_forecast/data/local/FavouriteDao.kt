package com.example.android.compose_udemy_bootcamp_course_weather_forecast.data.local

import androidx.room.*
import com.example.android.compose_udemy_bootcamp_course_weather_forecast.data.local.entity.Favourite
import com.example.android.compose_udemy_bootcamp_course_weather_forecast.data.local.entity.UnitSettings
import kotlinx.coroutines.flow.Flow

@Dao
interface FavouriteDao {

    @Query("SELECT * FROM favourite")
    fun getFavourites(): Flow<List<Favourite>>

    @Query("SELECT * FROM favourite WHERE city LIKE :city")
    suspend fun getFavById(city: String): Favourite

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavourite(favourite: Favourite)

    @Delete
    suspend fun deleteFavourite(favourite: Favourite)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateFavourite(favourite: Favourite)

    @Query("DELETE FROM favourite")
    suspend fun deleteAllFavourites()


    // UnitSettings entity
    @Query("SELECT * FROM unitsettings")
    fun getUnitSettings(): Flow<List<UnitSettings>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUnitSetting(unitSettings: UnitSettings)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateUnitSetting(unitSettings: UnitSettings)

    @Query("DELETE FROM unitsettings")
    suspend fun deleteAllUnitSettings()

    @Delete
    suspend fun deleteUnitSetting(unitSettings: UnitSettings)
}