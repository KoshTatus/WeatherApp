package com.example.weatherapp.data.models

import kotlinx.serialization.Serializable

@Serializable
data class CityResponse(
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val country: String,
    val population: Long,
    val region: String,
    val is_сapital: String
)
