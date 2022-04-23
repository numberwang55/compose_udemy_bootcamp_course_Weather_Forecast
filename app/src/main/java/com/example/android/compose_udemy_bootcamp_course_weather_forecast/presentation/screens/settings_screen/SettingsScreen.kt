package com.example.android.compose_udemy_bootcamp_course_weather_forecast.presentation.screens.settings_screen

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.android.compose_udemy_bootcamp_course_weather_forecast.data.local.entity.UnitSettings
import com.example.android.compose_udemy_bootcamp_course_weather_forecast.presentation.components.TopAppBarWeatherApp

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SettingsScreen(
    viewModel: SettingsScreenViewModel = hiltViewModel(),
    navController: NavController
) {
    val context = LocalContext.current
    var unitToggleState by remember { mutableStateOf(true) }
    val measurementUnits = listOf("metric", "imperial")
    val choiceFromDb = viewModel.unitSettingsList.collectAsState().value
    val defaultChoice = if (choiceFromDb.isNullOrEmpty()) measurementUnits[0]
        else choiceFromDb[0].unit
    var choiceState by remember { mutableStateOf(defaultChoice) }
    Scaffold(
        topBar = {
            TopAppBarWeatherApp(
                title = "Settings",
                navController = navController,
                icon = Icons.Default.ArrowBack,
                isMainScreen = false
            ) {
                navController.popBackStack()
            }
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Change Unit of Measurement",
                style = MaterialTheme.typography.h6,
                modifier = Modifier.padding(bottom = 12.dp)
            )
            IconToggleButton(
                checked = !unitToggleState,
                onCheckedChange = {
                    unitToggleState = !it
                    choiceState = if (unitToggleState) "metric" else "imperial"
                },
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .clip(RectangleShape)
                    .padding(5.dp)
                    .background(Color.Magenta.copy(alpha = 0.4f))
            ) {
                Text(
                    text = if (unitToggleState) "Celsius" else "Fahrenheit"
                )
            }
            Button(
                onClick = {
                    viewModel.deleteAllUnitSettings()
                    viewModel.insertUnitSetting(UnitSettings(
                        unit = choiceState
                    )).run {
                        Toast.makeText(
                            context,
                            "Settings saved",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                },
                modifier = Modifier
                    .padding(3.dp)
                    .align(Alignment.CenterHorizontally),
                shape = RoundedCornerShape(34.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xFFEFBE42)
                )
            ) {
                Text(
                    text = "Save",
                    modifier = Modifier.padding(4.dp),
                    color = Color.White,
                    fontSize = 17.sp
                )
            }
        }
    }
}