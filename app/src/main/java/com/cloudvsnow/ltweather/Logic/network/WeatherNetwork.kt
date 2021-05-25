package com.cloudvsnow.ltweather.Logic.network

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.await
import java.lang.RuntimeException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

object WeatherNetwork {
    private val placeInterface = ServiceCreator.create<PlaceInterface>()
    suspend fun searchPlaces(query: String) = placeInterface.searchPlaces(query).await()

    private val weatherInterface = ServiceCreator.create<WeatherInterface>()
    suspend fun getDailyWeather(lng: String, lat: String) = weatherInterface.getDailyWeather(lng, lat).await()
    suspend fun getRealtimeWeather(lng: String, lat: String) = weatherInterface.getRealtimeWeather(lng,lat).await()

    private suspend fun <T> Call<T>.await(): T {
        return suspendCoroutine {
            enqueue(object : Callback<T> {

                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val body = response.body()
                    if (body != null) it.resume(body)
                    else it.resumeWithException(RuntimeException("response body is null"))
                }

                override fun onFailure(call: Call<T>, t: Throwable) {
                    it.resumeWithException(t)
                }

            })
        }
    }



}

/*
* suspend关键字 让一个函数挂起，挂起函数间可以互相调用
* suspendCoroutine 挂起函数，在协程作用域中调用，堵塞当前协程，然后在普通线程中执行block中的代码
* await() 挂起函数，堵塞线程，等待当前协程任务完成
*
* */