package com.example.android.compose_udemy_bootcamp_course_weather_forecast.presentation.screens.favourites_screen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.compose_udemy_bootcamp_course_weather_forecast.data.local.entity.Favourite
import com.example.android.compose_udemy_bootcamp_course_weather_forecast.domain.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouriteScreenViewModel @Inject constructor(
    private val repository: WeatherRepository
) : ViewModel() {

//    private val _favList = MutableStateFlow<List<Favourite>>(emptyList())
//    val favList = _favList.asStateFlow()
    private var deletedFavourite: Favourite? = null

    val listOfFavourites = repository.getFavourites()

    init {
//        viewModelScope.launch(Dispatchers.IO) {
//            repository.getFavourites().distinctUntilChanged().collect { favouriteList ->
//                if (favouriteList.isNotEmpty()) _favList.value = favouriteList
//            }
//        }
    }

    fun insertFavourite(favourite: Favourite) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertFavourite(favourite)
        }
    }

    fun updateFavourite(favourite: Favourite) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateFavourite(favourite)
        }
    }

    fun deleteFavourite(favourite: Favourite) {
        viewModelScope.launch(Dispatchers.IO) {
            deletedFavourite = favourite
            repository.deleteFavourite(favourite)
        }
    }

    fun undoDeleteFavourite() {
        deletedFavourite?.let { favourite ->
            viewModelScope.launch(Dispatchers.IO) {
                repository.insertFavourite(favourite)
            }
        }
    }

}