package com.cloudvsnow.ltweather.Logic.network

import com.cloudvsnow.ltweather.Logic.model.DailyResponse
import com.cloudvsnow.ltweather.Logic.model.RealtimeResponse
import com.cloudvsnow.ltweather.WeatherApplication
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface WeatherInterface {
    @GET("v2.5/${WeatherApplication.TOKEN}/{lng},{lat}/realtime.json")
    fun getRealtimeWeather(@Path("lng") lng: String, @Path("lat") lat: String): Call<RealtimeResponse>

    @GET("v2.5/${WeatherApplication.TOKEN}/{lng},{lat}/daily.json")
    fun getDailyWeather(@Path("lng") lng: String, @Path("lat") lat: String): Call<DailyResponse>
}