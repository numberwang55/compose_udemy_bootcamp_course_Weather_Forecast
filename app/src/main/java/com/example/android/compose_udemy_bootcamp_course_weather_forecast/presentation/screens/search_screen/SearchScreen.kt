package com.example.android.compose_udemy_bootcamp_course_weather_forecast.presentation.screens.search_screen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.android.compose_udemy_bootcamp_course_weather_forecast.presentation.components.TopAppBarWeatherApp
import com.example.android.compose_udemy_bootcamp_course_weather_forecast.presentation.screens.main_screen.MainScreenViewModel
import com.example.android.compose_udemy_bootcamp_course_weather_forecast.util.Routes

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SearchScreen(
    navController: NavController
) {
    Scaffold(topBar = {
        TopAppBarWeatherApp(
            title = "Search",
            navController = navController,
            icon = Icons.Default.ArrowBack,
            isMainScreen = false,
            onButtonClick = { navController.popBackStack() }
        )
    }) {
        Surface() {
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SearchBar(modifier = Modifier
                    .padding(horizontal = 32.dp)
                    .align(Alignment.CenterHorizontally)
                ) {
                    navController.navigate(Routes.MAIN_SCREEN + "?city=${it}")
                }
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchBar(modifier: Modifier = Modifier, onSearch: (String) -> Unit) {
    val searchQueryState = rememberSaveable {
        mutableStateOf("")
    }
    val keyBoardController = LocalSoftwareKeyboardController.current
    val valid = remember(searchQueryState.value) {
        searchQueryState.value.trim().isNotEmpty()
    }

    CommonTextField(
        modifier = modifier,
        valueState = searchQueryState,
        placeholder = "e.g. London",
        onAction = KeyboardActions {
            if (!valid) return@KeyboardActions
            onSearch(searchQueryState.value.trim())
            searchQueryState.value = ""
            keyBoardController?.hide()
        }
    )
}

@Composable
fun CommonTextField(
    modifier: Modifier = Modifier,
    valueState: MutableState<String>,
    placeholder: String,
    keyBoardType: KeyboardType = KeyboardType.Text,
    imeActions: ImeAction = ImeAction.Next,
    onAction: KeyboardActions = KeyboardActions.Default
) {
    OutlinedTextField(
        value = valueState.value,
        onValueChange = { valueState.value = it },
        modifier = modifier
            .fillMaxWidth(),
        placeholder = { Text(text = placeholder) },
        label = { Text(text = "Enter city") },
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = keyBoardType, imeAction = imeActions),
        keyboardActions = onAction,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Blue,
            cursorColor = Color.Black
        ),
        shape = RoundedCornerShape(15.dp)
    )
}
