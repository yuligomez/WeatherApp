package com.example.weatherapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R

class RecyclerWeekDataAdapter : RecyclerView.Adapter<RecyclerWeekDataAdapter.ViewHolder>() {

    private var days = arrayOf("Monday", "Tuesday","Wednesday", "Thursday", "Friday", "Saturday","Sunday")
    private var temps_min = arrayOf("13º", "12º","9º", "10º", "11º", "20º","22º")
    private var temps_max = arrayOf("20º", "22º","34º", "30º", "23º", "33º","40º")
    private var weather_icons = intArrayOf(R.drawable.clima,R.drawable.clima, R.drawable.clima, R.drawable.clima, R.drawable.clima, R.drawable.clima, R.drawable.clima)

    override fun onCreateViewHolder( parent: ViewGroup, viewType: Int): RecyclerWeekDataAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_weather_items, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerWeekDataAdapter.ViewHolder, position: Int) {
        holder.item_day.text = days[position]
        holder.item_img_weather.setImageResource(weather_icons[position])
        holder.item_max_temp.text = temps_max[position]
        holder.item_min_temp.text = temps_min[position]
    }

    override fun getItemCount(): Int = days.size


    inner class ViewHolder(itemView : View) :RecyclerView.ViewHolder(itemView){
        var item_img_weather : ImageView
        var item_max_temp : TextView
        var item_min_temp : TextView
        var item_day : TextView

        init{
            item_img_weather = itemView.findViewById(R.id.img_weather_item_card)
            item_day = itemView.findViewById(R.id.tv_day_item)
            item_max_temp = itemView.findViewById(R.id.tv_max_item)
            item_min_temp= itemView.findViewById(R.id.tv_min_item)
        }
    }
}