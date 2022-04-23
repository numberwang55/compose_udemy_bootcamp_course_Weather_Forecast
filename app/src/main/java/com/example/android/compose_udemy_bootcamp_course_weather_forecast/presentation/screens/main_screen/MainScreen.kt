package com.example.android.compose_udemy_bootcamp_course_weather_forecast.presentation.screens.main_screen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.android.compose_udemy_bootcamp_course_weather_forecast.common.DataOrException
import com.example.android.compose_udemy_bootcamp_course_weather_forecast.data.remote.dto.WeatherDto
import com.example.android.compose_udemy_bootcamp_course_weather_forecast.data.remote.dto.WeatherObject
import com.example.android.compose_udemy_bootcamp_course_weather_forecast.presentation.components.*
import com.example.android.compose_udemy_bootcamp_course_weather_forecast.presentation.screens.about_screen.AboutScreen
import com.example.android.compose_udemy_bootcamp_course_weather_forecast.presentation.screens.settings_screen.SettingsScreenViewModel
import com.example.android.compose_udemy_bootcamp_course_weather_forecast.util.Routes
import com.example.android.compose_udemy_bootcamp_course_weather_forecast.util.formatDate
import com.example.android.compose_udemy_bootcamp_course_weather_forecast.util.formatDecimals

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(
    mainScreenViewModel: MainScreenViewModel = hiltViewModel(),
    settingsScreenViewModel: SettingsScreenViewModel = hiltViewModel(),
    navController: NavController,
    citySearched: String?
) {
    val isFromNoDataScreen = navController.previousBackStackEntry?.destination?.route == "no_data_screen"

    val defaultCity = if (citySearched.isNullOrBlank()) "Okehampton" else citySearched
    val unitFromDb = settingsScreenViewModel.unitSettingsList.collectAsState().value
    var unitOfMeasurementState by remember { mutableStateOf("metric") }
    var isImperialState by remember { mutableStateOf(false) }
    if (!unitFromDb.isNullOrEmpty()) {
        unitOfMeasurementState = unitFromDb[0].unit
        isImperialState = unitOfMeasurementState == "imperial"
        val weatherData =
            produceState<DataOrException<WeatherDto, Boolean, Exception>>(
                initialValue = DataOrException(isLoading = true)
            ) {
                value = mainScreenViewModel.getWeatherData(
                    city = defaultCity,
                    units = unitOfMeasurementState
                )
            }.value
        if (weatherData.isLoading == true) CircularProgressIndicator()
//        else if (weatherData.e != null) {
//            Column(
//                horizontalAlignment = Alignment.CenterHorizontally,
//                verticalArrangement = Arrangement.Center,
//                modifier = Modifier.fillMaxSize()
//            ) {
//                Text(
//                    text = "Connection error - please check your internet connection and restart",
//                    textAlign = TextAlign.Center
//                )
//            }
//        }
        else if (weatherData.data != null) {
            MainScreenScaffold(
                data = weatherData.data!!,
                navController = navController,
                isImperial = isImperialState
            )
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreenScaffold(
    data: WeatherDto,
    navController: NavController,
    isImperial: Boolean
) {
    Scaffold(
        topBar = {
            TopAppBarWeatherApp(
                title = data.city.name + ", ${data.city.country}",
                navController = navController,
                onAddActionClick = { navController.navigate(route = Routes.SEARCH_SCREEN) },
            )
        }
    ) {
        MainScreenScaffoldContent(data = data, isImperial = isImperial)
    }
}

@Composable
fun MainScreenScaffoldContent(data: WeatherDto, isImperial: Boolean) {
    val weatherDataItem = data.list[0]
    val imageUrl = "http://openweathermap.org/img/wn/${weatherDataItem.weather[0].icon}.png"

    Column(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = formatDate(weatherDataItem.dt),
            style = MaterialTheme.typography.body2,
            color = MaterialTheme.colors.onSecondary,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(6.dp)
        )
        Surface(
            modifier = Modifier
                .padding(4.dp)
                .size(200.dp),
            shape = CircleShape,
            color = Color(0xFFFFC400)
        )
        {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                WeatherStateImage(imageUrl)
                Text(
                    text = formatDecimals(weatherDataItem.main.temp) + "°",
                    style = MaterialTheme.typography.h4,
                    fontWeight = FontWeight.ExtraBold
                )
                Text(
                    text = weatherDataItem.weather[0].main,
                    fontStyle = FontStyle.Italic
                )
            }
        }
        HumidityWindPressureRow(data = weatherDataItem, isImperial = isImperial)
        Divider(modifier = Modifier.padding(bottom = 8.dp))
        SunriseSunsetRow(data = data)
        Text(
            text = "5 Day - 3 Hour Forecast",
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.subtitle1
        )
//        LazyColumn(modifier = Modifier.background(Color.LightGray)) {
//            itemsIndexed(data.list) { index, item->
//                WeekForecastItem(
//                    data = item,
////                    imageUrl = "http://openweathermap.org/img/wn/${data.list[index].weather[0].icon}.png"
//                )
//            }
//        }
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color(0xFFEEF1EF),
            shape = RoundedCornerShape(14.dp)
        ) {
            LazyColumn(
                modifier = Modifier.padding(2.dp),
                contentPadding = PaddingValues(1.dp)
            ) {
                items(items = data.list) { item: WeatherObject ->
                    WeatherDetailRow(data = item)
                }
            }
        }
    }
}