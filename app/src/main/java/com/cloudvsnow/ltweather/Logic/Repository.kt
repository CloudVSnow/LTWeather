package com.cloudvsnow.ltweather.Logic

import androidx.lifecycle.liveData
import com.cloudvsnow.ltweather.Logic.dao.PlaceDao
import com.cloudvsnow.ltweather.Logic.model.Place
import com.cloudvsnow.ltweather.Logic.model.WeatherModel
import com.cloudvsnow.ltweather.Logic.network.WeatherNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import okhttp3.Dispatcher
import java.lang.Exception
import java.lang.RuntimeException
import kotlin.coroutines.CoroutineContext

object Repository {
    fun searchPlaces(query: String) = fire(Dispatchers.IO) {
        val placeResponse = WeatherNetwork.searchPlaces(query)
        if (placeResponse.status == "ok") {
            val places = placeResponse.places
            Result.success(places)
        }else {
            Result.failure(RuntimeException("response status is${placeResponse.status}"))
        }
    }

    fun refreshWeather(lng: String, lat: String) = fire(Dispatchers.IO) {
        coroutineScope {
            val deferrdRealtime = async {
                WeatherNetwork.getRealtimeWeather(lng, lat)
            }
            val deferredDaily = async {
                WeatherNetwork.getDailyWeather(lng, lat)
            }
            val realtiemResponse = deferrdRealtime.await()
            val dailyResponse = deferredDaily.await()
            if (realtiemResponse.status == "ok" && dailyResponse.status == "ok" ) {
                val  weatherModel = WeatherModel(realtiemResponse.result.realtime, dailyResponse.result.daily)
                Result.success(weatherModel)

            }else {
                Result.failure(
                    RuntimeException("realtime response status is ${realtiemResponse.status}, daily response status is ${dailyResponse.status}")
                )
            }
        }
    }

    fun savePlace(place: Place) = PlaceDao.savePlace(place)

    fun getSavedPlace() = PlaceDao.getSavedPlace()

    fun isPlaceSaved() = PlaceDao.isPlaceSaved()

    private fun <T> fire(context: CoroutineContext, block: suspend () -> Result<T>) = liveData<Result<T>>(context) {
        val result = try {
            block()
        }catch (e: Exception) {
            Result.failure<T>(e)
        }
        emit(result)
    }
 }