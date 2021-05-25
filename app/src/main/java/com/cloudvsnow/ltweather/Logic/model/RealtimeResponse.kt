package com.cloudvsnow.ltweather.Logic.model

import com.google.gson.annotations.SerializedName

data class RealtimeResponse(val status: String, val result: Result) {

    data class Result(val realtime:RealTime)

    data class RealTime(val temperature: Float, val skycon: String, @SerializedName("air_quality")val airQuality: AriQuality)

    data class AriQuality(val aqi: AQI)

    data class AQI(val chn: Float)
}

