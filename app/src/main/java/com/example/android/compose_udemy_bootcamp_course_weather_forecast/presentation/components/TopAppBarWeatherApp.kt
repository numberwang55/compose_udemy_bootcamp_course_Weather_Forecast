package com.example.android.compose_udemy_bootcamp_course_weather_forecast.presentation.components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.android.compose_udemy_bootcamp_course_weather_forecast.data.local.entity.Favourite
import com.example.android.compose_udemy_bootcamp_course_weather_forecast.presentation.screens.favourites_screen.FavouriteScreenViewModel
import com.example.android.compose_udemy_bootcamp_course_weather_forecast.util.Routes
import okhttp3.internal.notify

@Composable
fun TopAppBarWeatherApp(
    modifier: Modifier = Modifier,
    title: String,
    icon: ImageVector? = null,
    isMainScreen: Boolean = true,
    elevation: Dp = 5.dp,
    navController: NavController,
    viewModel: FavouriteScreenViewModel = hiltViewModel(),
    onAddActionClick: () -> Unit = {},
    onButtonClick: () -> Unit = {}
) {
    val splitTitle = title.split(",")
    val isInFavList = viewModel.listOfFavourites.collectAsState(emptyList()).value.filter {
        (it.city == title.split(",")[0])
    }
    // MutableStateFlow
//    val isInFavList = viewModel.favList.collectAsState().value
    val context = LocalContext.current
    val showDialog = remember { mutableStateOf(false) }
    if (showDialog.value)
        ShowSettingDropDownMenu(showDialog = showDialog, navController = navController)
    TopAppBar(
        title = {
            Text(
                text = title,
                color = MaterialTheme.colors.onSecondary,
                style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp),
                modifier = modifier.fillMaxWidth(),
                textAlign = if (isMainScreen) TextAlign.Center else TextAlign.Start
            )
        },
        actions = {
            if (isMainScreen) {
                IconButton(onClick = { onAddActionClick() }) {
                    Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
                }
                IconButton(onClick = { showDialog.value = !showDialog.value }) {
                    Icon(imageVector = Icons.Rounded.MoreVert, contentDescription = "More")
                }
            } else {
                Box() {

                }
            }
        },
        navigationIcon = {
            if (icon != null) {
                IconButton(onClick = { onButtonClick() }) {
                    Icon(
                        imageVector = icon,
                        contentDescription = "Navigation Icon",
                        tint = MaterialTheme.colors.onSecondary
                    )
                }
            }
            if (isMainScreen && isInFavList.isEmpty()) {
                Icon(
                    imageVector = Icons.Default.FavoriteBorder,
                    contentDescription = "Favourite",
                    tint = Color.Red.copy(0.6f),
                    modifier = Modifier
                        .padding(8.dp)
                        .scale(0.9f)
                        .clickable {
                            val cityFromTitle = Favourite(
                                city = splitTitle[0],
                                country = splitTitle[1]
                            )
                            viewModel
                                .insertFavourite(
                                    Favourite(
                                        cityFromTitle.city,
                                        cityFromTitle.country
                                    )
                                )
                                .run {
                                    Toast
                                        .makeText(
                                            context,
                                            "City added to favourites",
                                            Toast.LENGTH_LONG
                                        )
                                        .show()
                                }
                        }
                )
            } else if (isMainScreen && isInFavList.isNotEmpty()) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "Favourite",
                    tint = Color.Red.copy(0.6f),
                    modifier = Modifier
                        .padding(8.dp)
                        .scale(0.9f)
                        .clickable {
                            val cityFromTitle = Favourite(
                                city = splitTitle[0],
                                country = splitTitle[1]
                            )
                            viewModel
                                .deleteFavourite(cityFromTitle)
                                .run {
                                    Toast
                                        .makeText(
                                            context,
                                            "City removed from favourites",
                                            Toast.LENGTH_LONG
                                        )
                                        .show()
                                }
                        }
                )
            }
        },
        backgroundColor = Color.Transparent,
        elevation = elevation,
    )
}

@Composable
fun ShowSettingDropDownMenu(showDialog: MutableState<Boolean>, navController: NavController) {
    var mExpanded by remember { mutableStateOf(true) }
    val items by remember {
        mutableStateOf(listOf("About", "Favourites", "Settings"))
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.TopEnd)
            .absolutePadding(top = 55.dp, right = 4.dp)
    ) {
        DropdownMenu(
            expanded = mExpanded,
            onDismissRequest = {
                mExpanded = false
                showDialog.value = false
            },
            modifier = Modifier
                .width(140.dp)
                .background(Color.White)
        ) {
            items.forEach { text ->
                DropdownMenuItem(onClick = {
                    mExpanded = false
                    showDialog.value = false
                }) {
                    Icon(
                        imageVector = when (text) {
                            "About" -> Icons.Default.Info
                            "Favourites" -> Icons.Default.Favorite
                            else -> Icons.Default.Settings
                        },
                        contentDescription = text,
                        tint = Color.LightGray
                    )
                    Text(
                        text = " $text",
                        modifier = Modifier.clickable {
                            navController.navigate(
                                route =
                                when (text) {
                                    "About" -> Routes.ABOUT_SCREEN
                                    "Favourites" -> Routes.FAVOURITE_SCREEN
                                    else -> Routes.SETTINGS_SCREEN
                                }
                            )
                        }, fontWeight = FontWeight(300)
                    )
                }
            }
        }
    }
}
