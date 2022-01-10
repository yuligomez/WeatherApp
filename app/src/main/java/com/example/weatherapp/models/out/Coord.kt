package com.example.weatherapp.models.out

import com.google.gson.annotations.SerializedName

class Coord {
    @SerializedName("lon")
    var lon = 0f

    @SerializedName("lat")
    var lat = 0f

}
