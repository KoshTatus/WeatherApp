package com.example.weatherapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.models.UiState
import com.example.weatherapp.data.models.WeatherResponse
import com.example.weatherapp.data.repositories.CityRepository
import com.example.weatherapp.data.repositories.WeatherRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val weatherRepository: WeatherRepository,
    private val cityRepository: CityRepository
) : ViewModel() {
    private val _isLoading = MutableStateFlow<UiState>(UiState.LOADING)
    val isLoading: StateFlow<UiState> = _isLoading

    private val _weatherData = MutableStateFlow<WeatherResponse?>(null)
    val weatherData: StateFlow<WeatherResponse?> = _weatherData

    fun getCityName(cityEngName: String): String = cityRepository.cities.find { it.nameEn == cityEngName }!!.name

    fun getCities() = cityRepository.cities

    fun fetchWeatherForCity(cityName: String) {
        viewModelScope.launch {
            weatherRepository.getCityData(cityName).collect{ cityResult ->
                if (cityResult.isSuccess){
                    val city = cityResult.getOrNull()!!
                    weatherRepository.getWeatherData(city.latitude, city.longitude).collect{ weatherResult ->
                        if (weatherResult.isSuccess){
                            _weatherData.value = weatherResult.getOrNull()
                            _isLoading.value = UiState.SUCCESS
                        }
                        else if (weatherResult.isFailure){
                            _isLoading.value = UiState.FAILED
                        }
                    }
                }
                else if (cityResult.isFailure){
                    _isLoading.value = UiState.FAILED
                }
            }
        }
    }
}