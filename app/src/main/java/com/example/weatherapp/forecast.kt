package com.example.weatherapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.databinding.FragmentForecastBinding

class ForecastFragment : Fragment() {
    val apiKey = "6998614644ea416aacc7729457dd1f83"
    val lat = 35.6895
    val lon = 139.6917
    private lateinit var binding: FragmentForecastBinding
    private lateinit var forecastAdapter: ForecatListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d("rec","before initializing")
        binding = FragmentForecastBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        forecastAdapter = ForecatListAdapter()
        binding.forecastRecycler.layoutManager = LinearLayoutManager(requireContext())
        Log.d("rec","layout initializing")
        binding.forecastRecycler.adapter = forecastAdapter
        forecastAdapter.setLis(SixteenDays.sixteenDaysList)


        binding.fab.setOnClickListener{
            val d1 = ForecastData(30.1,7,47.0,3.0,3.0,"2024-8-19")
            val d2 = ForecastData(30.1,7,47.0,3.0,3.0,"2024-8-25")
            val d3 = ForecastData(30.1,7,47.0,3.0,3.0,"2024-8-30")
            forecastAdapter.setLis(mutableListOf(d1,d2,d3))

        }



    }




}
