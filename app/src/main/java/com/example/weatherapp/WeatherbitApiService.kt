package com.example.weatherapp
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
interface WeatherbitApiService {
    @GET("forecast/daily")
     fun getCurrentWeather(
        @Query("lat") lat:Double,
        @Query("lon") lon:Double,
        @Query("key") apiKey: String
    ):Call<WeatherbitResponse>
}