package com.example.weatherapp.activities

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.weatherapp.R
import com.example.weatherapp.adapters.RecyclerWeekDataAdapter
import com.example.weatherapp.models.out.WeatherModel
import com.example.weatherapp.services.WeatherApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers


private const val TAG = "TodayWeekWeather"

class TodayWeekWeather : AppCompatActivity() {

    private var adapter : RecyclerView.Adapter<RecyclerWeekDataAdapter.ViewHolder>? = null
    private var recycler : RecyclerView? = null
    private lateinit var textView_city : TextView
    private lateinit var textView_degree : TextView
    private lateinit var textView_weather_desc : TextView
    private lateinit var temp_max_min : TextView
    private lateinit var img_weather : ImageView


    private val weatherApiService = WeatherApiService()
    private val disposable = CompositeDisposable()

    private val weather_data = MutableLiveData<WeatherModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_today_week_weather)
        initViews()
        initVars()
        val cityName = "montevideo"
        getDataFromAPI(cityName)
    }

    private fun getDataFromAPI(cityName: String) {

   /*    disposable.add(
            weatherApiService.getCurrentWeatherData( -34.90328, -56.18816, "minutely,alerts")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<WeatherModel>() {

                    override fun onSuccess(t: WeatherModel) {
                        weather_data.value = t
                        getLiveData()
                        Log.d(TAG, "onSuccess: Success")
                    }

                    override fun onError(e: Throwable) {

                        Log.e(TAG, "onError: " + e)
                    }
                })
        )
*/

            disposable.add(
            weatherApiService.getDataService(cityName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<WeatherModel>() {

                    override fun onSuccess(t: WeatherModel) {
                        weather_data.value = t
                        getLiveData()
                        Log.d(TAG, "onSuccess: Success")
                    }
                    override fun onError(e: Throwable) {

                        Log.e(TAG, "onError: " + e)
                    }
                })
        )
    }

    private fun initViews (){
        recycler = findViewById(R.id.rv_week_weather)
        textView_city = findViewById<View>(R.id.tv_city) as TextView
        textView_degree = findViewById<View>(R.id.tv_degree) as TextView
        img_weather = findViewById<View>(R.id.img_weather) as ImageView
        textView_weather_desc = findViewById<View>(R.id.tv_weather_description) as TextView
        temp_max_min = findViewById<View>(R.id.tv_max_min_temp) as TextView

    }
    private fun initVars(){

        adapter = RecyclerWeekDataAdapter()
        recycler?.layoutManager = LinearLayoutManager(this)
        recycler?.adapter = adapter
    }

    private fun getLiveData() {

        weather_data.observe(this, Observer { data ->
            data?.let {

            }
                textView_city.text = data.name.toString()
                textView_degree.text = data.main?.temp.toString()
                textView_weather_desc.text = data.weather.get(0).description.toString()
                //Máx.:30ºC Mín.:17ºC
                temp_max_min.text = "Máx.: "+ data.main?.temp_min.toString() + "ºC" + " "+ "Mín.: "+ data.main?.temp_max.toString() + "ºC"
                Glide.with(this)
                    .load("https://openweathermap.org/img/wn/" + data.weather.get(0).icon + "@2x.png")
                    .into(img_weather)


        })
    }

}

