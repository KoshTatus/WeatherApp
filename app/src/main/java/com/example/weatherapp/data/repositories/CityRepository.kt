package com.example.weatherapp.data.repositories

import com.example.weatherapp.R
import com.example.weatherapp.data.models.City

class CityRepository {
    val cities = listOf(
        City("Ижевск", "Izhevsk",R.drawable.izhevsk),
        City("Воткинск", "Votkinsk",R.drawable.votkinsk),
        City("Можга", "Mozhga",R.drawable.mozhga),
        City("Глазов", "Glazov",R.drawable.glazov),
        City("Сарапул", "Sarapul",R.drawable.sarapul),
        City("Камбарка", "Kambarka",R.drawable.kambarka)
    )
}