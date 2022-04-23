package com.example.android.compose_udemy_bootcamp_course_weather_forecast.di

import android.app.Application
import androidx.room.Room
import com.example.android.compose_udemy_bootcamp_course_weather_forecast.common.Constants
import com.example.android.compose_udemy_bootcamp_course_weather_forecast.data.local.FavouriteDatabase
import com.example.android.compose_udemy_bootcamp_course_weather_forecast.data.remote.WeatherApi
import com.example.android.compose_udemy_bootcamp_course_weather_forecast.data.remote.dto.Weather
import com.example.android.compose_udemy_bootcamp_course_weather_forecast.data.repository.WeatherRepositoryImpl
import com.example.android.compose_udemy_bootcamp_course_weather_forecast.domain.repository.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideFavouriteDatabase(app: Application): FavouriteDatabase {
        return Room.databaseBuilder(
            app,
            FavouriteDatabase::class.java,
            "favourite_db"
        ).createFromAsset("database/weather.db").build()
    }
//    .fallbackToDestructiveMigration()

    @Provides
    @Singleton
    fun provideWeatherRepository(api: WeatherApi, db: FavouriteDatabase): WeatherRepository {
        return WeatherRepositoryImpl(api = api, dao = db.dao)
    }

    @Provides
    @Singleton
    fun provideWeatherApi(): WeatherApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApi::class.java)
    }
}