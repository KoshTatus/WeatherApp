package com.example.weatherapp

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.compose.rememberNavController
import com.example.weatherapp.ui.components.NavHost
import com.example.weatherapp.ui.viewmodels.WeatherViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun App(weatherViewModel: WeatherViewModel){
    val navController = rememberNavController()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ){
        Image(
            painter = painterResource(R.drawable.starjpg),
            modifier = Modifier.fillMaxSize(),
            contentDescription = "Star"
        )
        NavHost(navController, weatherViewModel)
    }

}