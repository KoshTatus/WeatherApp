package com.example.weatherapp.data.api

import com.example.weatherapp.data.models.CityResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface CityApi {
    @GET("v1/city")
    suspend fun getCityData(
        @Query("name") name: String,
        @Header("X-Api-Key") api: String = "YOUR_API_KEY"
    ): Response<List<CityResponse>>
}

object CityClient {

    private const val BASE_URL = "https://api.api-ninjas.com"

    val api: CityApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CityApi::class.java)
    }
}
