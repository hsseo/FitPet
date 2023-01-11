package com.example.kotlinproject.ui.page.view.weather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinproject.data.repository.weather.RepositoryWeather

class WeatherViewModelFactory(repository: RepositoryWeather) : ViewModelProvider.Factory {

    var repository : RepositoryWeather?= repository

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(WeatherViewModel::class.java)) {
            return WeatherViewModel(repository!!) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}