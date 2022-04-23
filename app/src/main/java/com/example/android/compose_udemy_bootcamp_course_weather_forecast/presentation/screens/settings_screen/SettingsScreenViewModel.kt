package com.example.android.compose_udemy_bootcamp_course_weather_forecast.presentation.screens.settings_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.compose_udemy_bootcamp_course_weather_forecast.data.local.entity.UnitSettings
import com.example.android.compose_udemy_bootcamp_course_weather_forecast.domain.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsScreenViewModel @Inject constructor(
    private val repository: WeatherRepository
) : ViewModel() {

    private val _unitSettingsList = MutableStateFlow<List<UnitSettings>>(emptyList())
    val unitSettingsList = _unitSettingsList.asStateFlow()
    private var deletedUnitSetting: UnitSettings? = null

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getUnitSettings().distinctUntilChanged().collect {
                if (!it.isNullOrEmpty())
                    _unitSettingsList.value = it
            }
        }
    }

    fun insertUnitSetting(unitSettings: UnitSettings) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertUnitSetting(unitSettings)
        }
    }

    fun updateUnitSetting(unitSettings: UnitSettings) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateUnitSetting(unitSettings)
        }
    }

    fun deleteUnitSetting(unitSettings: UnitSettings) {
        viewModelScope.launch(Dispatchers.IO) {
            deletedUnitSetting = unitSettings
            repository.deleteUnitSetting(unitSettings)
        }
    }

    fun deleteAllUnitSettings() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllUnitSettings()
        }
    }

    fun undoUnitSetting() {
        deletedUnitSetting?.let { unitSettings ->
            viewModelScope.launch(Dispatchers.IO) {
                repository.insertUnitSetting(unitSettings)
            }
        }
    }
}