package com.example.weatherapp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://api.weatherbit.io/v2.0/ " // Removed leading space

    val instance: WeatherbitApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL) // Use BASE_URL
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherbitApiService::class.java)
    }
}
