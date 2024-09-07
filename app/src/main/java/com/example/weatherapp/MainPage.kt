package com.example.weatherapp

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.weatherapp.databinding.FragmentMainPageBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class MainPage : Fragment() {

    private lateinit var binding: FragmentMainPageBinding
    private val weatherViewModel: WeatherbitVM by viewModels()
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private val apiKey = "6998614644ea416aacc7729457dd1f83"

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1001
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainPageBinding.inflate(inflater, container, false)
        return binding.root
    }

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireContext())

        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            getLocation()
        } else {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE
            )
        }

        binding.FiveDayForecast.setOnClickListener {
            findNavController().navigate(R.id.action_mainPage_to_fiveDayForecast)
        }

        weatherViewModel.forecastData.observe(viewLifecycleOwner, Observer { forecastData ->
            forecastData?.let { dataList ->
                binding.tempratureText.text = "${dataList.first().temp}°C"
                binding.AQIText.text = "${dataList.first().vis}"
                binding.windSpeedText.text = "${dataList.first().wind_spd} km/h"
                binding.humidityText.text = "${dataList.first().rh} %"
                binding.UVIndexText.text = "${dataList.first().uv}"
                binding.weatherStateText.text = "${dataList.first().weather.description}"
                binding.todayDateText.text = "${dataList.first().valid_date}"
                SixteenDays.sixteenDaysList.clear()
                dataList.forEach {
                    SixteenDays.sixteenDaysList.add(ForecastData(it.temp,it.uv,it.vis,it.rh,it.wind_spd,it.valid_date))

                }

                Log.d("UI", "Forecast data updated: ${dataList.size} entries")
            } ?: run {
                Log.e("UI", "No forecast data available to update the UI")
            }
        })
    }

    private fun getLocation() {
        try {
            if (ContextCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                fusedLocationProviderClient.lastLocation.addOnSuccessListener { location ->
                    location?.let {
                        var lat = it.latitude
                        var lon = it.longitude
                        Log.d("loc","${lat}, ${lon}")

                        weatherViewModel.fetchWeather(lat, lon, apiKey)
                    }
                }.addOnFailureListener {
                    Log.d("loc", "Failed to access location")
                }
            } else {
                Log.e("Permission", "Location permission not granted")
            }
        } catch (e: SecurityException) {
            Log.e("SecurityException", "Error accessing location: ${e.message}")
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLocation()
            } else {
                Log.e("Permission", "Location permission denied")
            }
        }
    }
}
