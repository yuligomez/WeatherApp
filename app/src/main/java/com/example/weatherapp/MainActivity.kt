package com.example.weatherapp


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.weatherapp.activities.TodayWeekWeather
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {


    private lateinit var bundle: Bundle
    val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activiy_main)
        initVars()


        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when(item.itemId) {
                R.id.nav_climate -> {


                    true
                }
                R.id.nav_location -> {

                    true
                }
                else -> false
            }
        }
    }

    private fun initVars() {
        val intent = Intent(this, TodayWeekWeather::class.java)
        startActivity(intent)

    }

   /* private fun setCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_wrapper, fragment)
            commit()
        }*/


}