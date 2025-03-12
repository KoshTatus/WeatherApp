package com.example.weatherapp.ui.screens

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.weatherapp.ui.components.WeatherCard
import com.example.weatherapp.ui.viewmodels.WeatherViewModel
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun WeatherScreen(
    navController: NavController,
    cityName: String,
    weatherViewModel: WeatherViewModel
){
    weatherViewModel.fetchWeatherForCity(cityName)
    val weatherData by weatherViewModel.weatherData.collectAsState()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 23.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { navController.popBackStack() }) {
            Icon(
                Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back"
            )
        }
    }

    if (weatherData != null){
        val hourlyData = weatherData!!.hourly
        val times = hourlyData.time
        val temps = hourlyData.temperature2m

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 25.dp),
            horizontalArrangement = Arrangement.Center
        ){
            Text(
                text = "Погода в г. ${weatherViewModel.getCityName(cityName)}",
                style = MaterialTheme.typography.headlineLarge
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(65.dp),
            horizontalArrangement = Arrangement.Center
        ){
            Text(
                text = LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM")),
                style = MaterialTheme.typography.headlineLarge
            )
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 100.dp)
        ) {
            items(times.zip(temps)){ (time, temp) ->
                WeatherCard(formatDateTime(time), temp)
            }
        }
    }
    else{
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(65.dp),
            horizontalArrangement = Arrangement.Center
        ){
            Text(
                text = "Нет подключения к Интернету",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.displaySmall
            )
        }
    }
}

fun formatDateTime(inputDateTime: String): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm", Locale.getDefault())
    val outputFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
    val date = inputFormat.parse(inputDateTime) ?: return ""

    return outputFormat.format(date)
}