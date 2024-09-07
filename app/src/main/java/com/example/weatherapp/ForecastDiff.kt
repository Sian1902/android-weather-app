package com.example.weatherapp

import androidx.recyclerview.widget.DiffUtil

class ForecastDiff(val oldList:List<ForecastData>, val newList: List<ForecastData>): DiffUtil.Callback() {
    override fun getOldListSize()=oldList.size

    override fun getNewListSize()=newList.size
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
       return oldList[oldItemPosition]==newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].date==newList[newItemPosition].date
    }
}