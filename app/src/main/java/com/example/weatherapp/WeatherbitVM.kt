package com.example.weatherapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherbitVM : ViewModel() {

    // LiveData for the entire forecast
    private val _forecastData = MutableLiveData<List<Data>>()
    val forecastData: LiveData<List<Data>> get() = _forecastData

    fun generateWeatherDataList():MutableList<ForecastData>{
        val forecastList= mutableListOf<ForecastData>()
        forecastData.value?.forEach {
            //forecastList.add(ForecastData(it.temp,it.uv,it.vis,it.rh,it.wind_spd,it.valid_date))
        }
        return forecastList
    }

    fun fetchWeather(lat: Double, lon: Double, apiKey: String) {
        val call = RetrofitClient.instance.getCurrentWeather(
            lat = lat,
            lon = lon,
            apiKey = apiKey
        )

        call.enqueue(object : Callback<WeatherbitResponse> {
            override fun onResponse(call: Call<WeatherbitResponse>, response: Response<WeatherbitResponse>) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    Log.d("WeatherAPI", "Response received: $responseBody")

                    if (responseBody != null && responseBody.data.isNotEmpty()) {
                        // Set the entire forecast
                        _forecastData.value = responseBody.data
                    } else {
                        Log.e("WeatherAPI", "No weather data available in response")
                        _forecastData.value = emptyList()
                    }
                } else {
                    Log.e("WeatherAPI", "Response error: ${response.errorBody()}")
                }
            }

            override fun onFailure(call: Call<WeatherbitResponse>, t: Throwable) {
                Log.e("WeatherAPI", "API call failed", t)
            }
        })
    }
}
