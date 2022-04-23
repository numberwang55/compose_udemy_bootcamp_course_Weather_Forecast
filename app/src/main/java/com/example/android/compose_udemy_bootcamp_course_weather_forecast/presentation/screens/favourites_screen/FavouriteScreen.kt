package com.example.android.compose_udemy_bootcamp_course_weather_forecast.presentation.screens.favourites_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.android.compose_udemy_bootcamp_course_weather_forecast.data.local.entity.Favourite
import com.example.android.compose_udemy_bootcamp_course_weather_forecast.presentation.components.TopAppBarWeatherApp
import com.example.android.compose_udemy_bootcamp_course_weather_forecast.util.Routes
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun FavouritesScreen(
    viewModel: FavouriteScreenViewModel = hiltViewModel(),
    navController: NavController
) {
    val listOfFav = viewModel.listOfFavourites.collectAsState(initial = emptyList())
    // MutableStateFlow
//    val favList = viewModel.favList.collectAsState().value
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBarWeatherApp(
                title = "Favourite Cities",
                navController = navController,
                isMainScreen = false,
                icon = Icons.Default.ArrowBack
            ) {
                navController.popBackStack()
            }
        }
    ) {
        Surface(
            modifier = Modifier
                .padding(5.dp)
                .fillMaxSize(),
            color = Color(0xFFEEF1EF),
        ) {
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LazyColumn {
                    items(listOfFav.value) { favourite ->
                        FavouriteCitiesRow(favourite, navController) {
                            viewModel.deleteFavourite(favourite)
                            coroutineScope.launch {
                                val result = scaffoldState.snackbarHostState.showSnackbar(
                                    message = "City Deleted",
                                    actionLabel = "Undo"
                                )
                                if (result == SnackbarResult.ActionPerformed) {
                                    viewModel.undoDeleteFavourite()
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun FavouriteCitiesRow(
    favourite: Favourite,
    navController: NavController,
    onDeleteClick: () -> Unit
) {
    if (favourite.country.isEmpty()){
        Box() {

        }
    }
    Surface(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth(),
        shape = CircleShape,
        color = Color.White
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .clickable {
                    navController.navigate(Routes.MAIN_SCREEN + "?city=${favourite.city}")
                }
        ) {
            Text(text = favourite.city)
            Text(text = favourite.country, style = MaterialTheme.typography.caption)
            Spacer(modifier = Modifier.width(8.dp))
            IconButton(
                onClick = { onDeleteClick() },
            ) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete")
            }
        }
    }
}

@Preview
@Composable
fun FavouriteCitiesRowPreview(
    favourite: Favourite = Favourite("London", "GB"),
    onDeleteClick: () -> Unit = {}
) {
    Surface(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth(),
        shape = CircleShape,
        color = Color.White
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(
                verticalArrangement = Arrangement.Center
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .clickable {}
                ) {
                    Text(text = favourite.city)
                    Text(text = favourite.country)
                }
                IconButton(
                    onClick = { onDeleteClick() },
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete")
                }
            }
        }
    }
}