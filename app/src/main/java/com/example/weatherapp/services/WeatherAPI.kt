package com.example.weatherapp.services

import com.example.weatherapp.models.out.WeatherModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {


    //http://api.openweathermap.org/data/2.5/weather?q=montevideo&APPID={API key}

    @GET("data/2.5/weather?&units=metric&APPID=7c2a6d3fafcd6dc048825b923a10502e")
    fun getData(
        @Query("q") cityName: String
    ): Single<WeatherModel>


    //https://api.openweathermap.org/data/2.5/onecall?lat=33.44&lon=-94.04&exclude=hourly,daily&appid={API key}
    @GET("data/2.5/onecall?&APPID=7c2a6d3fafcd6dc048825b923a10502e")
    fun getCurrentWeatherData(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("exclude") exclude: String?,
    ): Single<WeatherModel>
}