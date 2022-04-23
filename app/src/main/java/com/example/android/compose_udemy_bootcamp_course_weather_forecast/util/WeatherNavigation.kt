package com.example.android.compose_udemy_bootcamp_course_weather_forecast.util

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.android.compose_udemy_bootcamp_course_weather_forecast.presentation.screens.about_screen.AboutScreen
import com.example.android.compose_udemy_bootcamp_course_weather_forecast.presentation.screens.favourites_screen.FavouritesScreen
import com.example.android.compose_udemy_bootcamp_course_weather_forecast.presentation.screens.main_screen.MainScreen
import com.example.android.compose_udemy_bootcamp_course_weather_forecast.presentation.screens.no_data_screen.NoDataScreen
import com.example.android.compose_udemy_bootcamp_course_weather_forecast.presentation.screens.search_screen.SearchScreen
import com.example.android.compose_udemy_bootcamp_course_weather_forecast.presentation.screens.settings_screen.SettingsScreen
import com.example.android.compose_udemy_bootcamp_course_weather_forecast.presentation.screens.splash_screen.WeatherSplashScreen

@Composable
fun WeatherNavigation(
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        startDestination = Routes.SPLASH_SCREEN
    ) {
        composable(Routes.SPLASH_SCREEN) {
            WeatherSplashScreen(navController = navHostController)
        }
        composable(
            Routes.MAIN_SCREEN + "?city={city}",
            arguments = listOf(
                navArgument(name = "city") {
                    type = NavType.StringType
                }
            )
        ) {
            it.arguments?.getString("city").let { city ->
                MainScreen(navController = navHostController, citySearched = city)
            }
        }
        composable(Routes.SEARCH_SCREEN) {
            SearchScreen(navController = navHostController)
        }
        composable(Routes.ABOUT_SCREEN) {
            AboutScreen(navController = navHostController)
        }
        composable(Routes.FAVOURITE_SCREEN) {
            FavouritesScreen(navController = navHostController)
        }
        composable(Routes.SETTINGS_SCREEN) {
            SettingsScreen(navController = navHostController)
        }
        composable(Routes.NO_DATA_SCREEN) {
            NoDataScreen(navController = navHostController)
        }
    }
}