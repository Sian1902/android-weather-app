package com.example.weatherapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.databinding.ForecastRowBinding
import com.example.weatherapp.databinding.RowBinding

class ForecatListAdapter():RecyclerView.Adapter<ForecatListAdapter.MyViewHolder>(){
/*    lateinit var binding: ForecastRowBinding*/
private var forecastList= mutableListOf<ForecastData>()
    fun setLis(list:MutableList<ForecastData>){
        val diffCallback= ForecastDiff(forecastList,list)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        forecastList=list
        diffResult.dispatchUpdatesTo(this)
    }
    companion object{
        const val type1=1
        const val type2=2
    }
    class MyViewHolder(val binding: ForecastRowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item:ForecastData){


          binding.tempText.text= item.temp.toString()
            binding.UVText.text=item.uv.toString()
            binding.visibilityText.text=item.aqi.toString()
            binding.windText.text=item.windSpeed.toString()
            binding.humidityText.text=item.humidity.toString()
            binding.dateText.text=item.date
        }
    }
    class userHolder(val binding: RowBinding):RecyclerView.ViewHolder(binding.root){
        fun binnd(item:UserData){
            binding.mail.text=item.mail

            binding.userName.text=item.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
                val binding= ForecastRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return MyViewHolder(binding)
        }

    override fun onBindViewHolder(holder:MyViewHolder , position: Int) {
        val currentItem= forecastList[position]
        holder.bind(currentItem)
    }


    override fun getItemCount(): Int =forecastList.size


}