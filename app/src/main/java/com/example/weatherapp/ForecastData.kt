package com.example.weatherapp

data class ForecastData(val temp:Double,
                        val uv:Int, val aqi:Double, val humidity:Double,
                        val windSpeed:Double, val date:String)
