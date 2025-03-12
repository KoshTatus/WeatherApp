package com.example.weatherapp.ui.components

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.weatherapp.ui.screens.CityList
import com.example.weatherapp.ui.screens.WeatherScreen
import com.example.weatherapp.ui.viewmodels.WeatherViewModel

@Composable
fun NavHost(navController: NavHostController, weatherViewModel: WeatherViewModel){
    NavHost(navController = navController, startDestination = "cities"){
        composable("cities"){
            val cities = weatherViewModel.getCities()
            CityList(navController, cities)
        }

        composable("cities/{city}"){ backStackEntry ->
            val cityName = backStackEntry.arguments?.getString("city").toString()
            WeatherScreen(navController, cityName, weatherViewModel)
        }
    }
}