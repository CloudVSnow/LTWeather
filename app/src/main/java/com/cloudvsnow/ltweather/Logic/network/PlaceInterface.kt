package com.cloudvsnow.ltweather.Logic.network

import com.cloudvsnow.ltweather.Logic.model.PlaceResponse
import com.cloudvsnow.ltweather.WeatherApplication
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PlaceInterface {
    @GET("v2/place?token=${WeatherApplication.TOKEN}&lang=zh_CN")
    fun searchPlaces(@Query("query") query: String): Call<PlaceResponse>
}