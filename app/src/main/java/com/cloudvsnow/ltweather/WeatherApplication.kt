package com.cloudvsnow.ltweather

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class WeatherApplication: Application() {

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context

        const val TOKEN = "yaR96P1y2VEO92JV"
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}