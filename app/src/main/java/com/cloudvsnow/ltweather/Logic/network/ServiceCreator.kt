package com.cloudvsnow.ltweather.Logic.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object ServiceCreator {
    private const val HOST = "https://api.caiyunapp.com/"

    private val retorfit = Retrofit.Builder()
        .baseUrl(HOST)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun <T> create(serviceClass: Class<T>): T = retorfit.create(serviceClass)

    inline fun <reified T> create(): T = create(T::class.java)
}