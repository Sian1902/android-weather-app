package com.example.weatherapp

data class WeatherbitResponse(val count: Int,
                              val data: List<Data>,
                              val minutely: List<String>)
