package com.example.weatherapp.data.repositories

import com.example.weatherapp.data.api.CityApi
import com.example.weatherapp.data.api.WeatherApi
import com.example.weatherapp.data.models.CityResponse
import com.example.weatherapp.data.models.WeatherResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class WeatherRepository(
    private val weatherApi: WeatherApi,
    private val cityApi: CityApi
) {
    fun getCityData(cityName: String): Flow<Result<CityResponse>> = flow {
        try{
            val response = cityApi.getCityData(cityName)
            if (response.isSuccessful && response.body() != null){
                val body = response.body()!!.firstOrNull()!!

                emit(Result.success(body))
            }
            else{
                emit(Result.failure(Exception("City not found")))
            }
        } catch (e: Exception){
            emit(Result.failure(e))
        }
    }
    fun getWeatherData(latitude: Double, longitude: Double): Flow<Result<WeatherResponse>> = flow {
        try{
            val response = weatherApi.getWeatherForecast(latitude, longitude)
            if (response.isSuccessful){
                emit(Result.success(response.body()!!))
            }
            else{
                emit(Result.failure(Exception("Can not fetch data")))
            }
        } catch (e: Exception){
            emit(Result.failure(e))
        }
    }
}