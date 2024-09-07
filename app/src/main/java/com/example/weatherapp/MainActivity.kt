package com.example.weatherapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Window
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.example.weatherapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    val binding:ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(binding.root)
        Log.d("nav","begin nav initialization")
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_hos_fragment) as NavHostFragment
        Log.d("nav","nav found")
        val navController = navHostFragment.navController

       /* val appBarConfiguration = AppBarConfiguration(navController.graph)*/
    }
}
