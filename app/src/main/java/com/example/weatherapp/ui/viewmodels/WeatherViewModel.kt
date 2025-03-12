package com.example.weatherapp.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    private val _weatherData = MutableStateFlow<WeatherResponse?>(null)
    val weatherData: StateFlow<WeatherResponse?> = _weatherData

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

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
                        }
                        else if (weatherResult.isFailure){
                            _error.value = weatherResult.exceptionOrNull()!!.message
                        }
                    }
                }
                else if (cityResult.isFailure){
                    _error.value = cityResult.exceptionOrNull()!!.message
                }
            }
        }
    }
}