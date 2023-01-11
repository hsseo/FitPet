package com.example.kotlinproject.ui.page.view.weather.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinproject.app_base.adapter.BaseAdapter
import com.example.kotlinproject.app_base.adapter.CallbackAdapter
import com.example.kotlinproject.data.domain.weather.DtWeather
import com.example.kotlinproject.databinding.AdWeatherDetailBinding

class AdapterWeatherDetail() : BaseAdapter() {
    constructor(listener : CallbackAdapter) : this() {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderWeatherDetail {
        val binding : AdWeatherDetailBinding = AdWeatherDetailBinding.inflate(LayoutInflater.from(parent.context))
        return HolderWeatherDetail(binding, listener!!)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is HolderWeatherDetail) {
            holder.binding.apply {
                dataWeather = getWeather(position)
                executePendingBindings()
            }
        }
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    fun getWeather(position: Int) : DtWeather {
        val data : DtWeather = datas.get(position) as DtWeather
        data.isFirst = isFirstItem(position)
        return data
    }

    fun isFirstItem(position: Int) : Boolean{
        return position == 0
    }

    @SuppressLint("NotifyDataSetChanged")
    fun add(data : DtWeather) {
        this.datas.clear()
        this.datas.addAll(data.weatherTime)
        list?.emptyList(false)
        notifyDataSetChanged()
    }
}