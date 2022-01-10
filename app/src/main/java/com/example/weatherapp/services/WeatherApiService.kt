package com.example.weatherapp.services


import com.example.weatherapp.models.out.WeatherModel
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class WeatherApiService {

    //http://api.openweathermap.org/data/2.5/weather?q=bingol&APPID=7c2a6d3fafcd6dc048825b923a10502e

    private val BASE_URL = "http://api.openweathermap.org/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(WeatherAPI::class.java)

    fun getDataService(cityName: String): Single<WeatherModel> {
        return retrofit.getData(cityName)
    }

    fun getCurrentWeatherData(lat: Double, long: Double, exclude: String): Single<WeatherModel> {
        return retrofit.getCurrentWeatherData(lat,long,exclude)
    }

}




