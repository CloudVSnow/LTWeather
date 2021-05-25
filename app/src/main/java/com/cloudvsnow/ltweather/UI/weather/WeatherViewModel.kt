package com.cloudvsnow.ltweather.UI.weather

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.cloudvsnow.ltweather.Logic.Repository
import com.cloudvsnow.ltweather.Logic.model.Location

class WeatherViewModel: ViewModel() {

    private val locationLiveData = MutableLiveData<Location>()
    var longitude: String = ""
    var latitude: String = ""
    var placeName: String = ""

    fun refreshWeather(lng: String, lat: String) {
        locationLiveData.value = Location(lng, lat)
    }

    val weatherData = Transformations.switchMap(locationLiveData) {
        location -> Repository.refreshWeather(location.lng, location.lat)
    }
}