package com.cloudvsnow.ltweather.Logic.model

data class WeatherModel(val realtime: RealtimeResponse.RealTime, val daily: DailyResponse.Daily)
