package com.example.weatherapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.weatherapp.data.models.City
import com.example.weatherapp.ui.components.CityCard

@Composable
fun CityList(
    navController: NavController,
    cities: List<City>,
){
    Row(
        modifier = Modifier.fillMaxWidth().padding(30.dp),
        horizontalArrangement = Arrangement.Center
    )
    {
        Text(
            text = "Погода в Удмуртии",
            style = MaterialTheme.typography.displaySmall
        )
    }
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 80.dp)
    ) {
        items(cities){ city ->
            CityCard(city) {
                navController.navigate("cities/${city.nameEn}")
            }
        }
    }
}

