package com.example.kotlinproject.ui.page.view.weather.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinproject.app_base.adapter.BaseAdapter
import com.example.kotlinproject.app_base.adapter.CallbackAdapter
import com.example.kotlinproject.data.domain.weather.DtWeather
import com.example.kotlinproject.data.model.ResponseWeather
import com.example.kotlinproject.databinding.AdWeatherBinding

class AdapterWeather() : BaseAdapter() {
    constructor(listener : CallbackAdapter) : this() {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderWeather {
        val binding : AdWeatherBinding = AdWeatherBinding.inflate(LayoutInflater.from(parent.context))
        return HolderWeather(binding, listener!!)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is HolderWeather) {
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
        if(position == 0) {
            return true;
        } else if(position == datas.size - 1) {
            return false
        }
        val currentDt : DtWeather = datas.get(position) as DtWeather
        val beforeDt : DtWeather = datas.get(position - 1) as DtWeather
        if(currentDt.city!!.id == beforeDt.city!!.id) {
            return false
        }
        return true
    }

    @SuppressLint("NotifyDataSetChanged")
    fun add(datas : List<ResponseWeather>) {
        this.datas.clear()
        for(data : ResponseWeather in datas) {
            this.datas.addAll(data.getWeatherDatas())
        }
        list?.emptyList(false)
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun clearDatas() {
        datas.clear()
        notifyDataSetChanged()
    }
}