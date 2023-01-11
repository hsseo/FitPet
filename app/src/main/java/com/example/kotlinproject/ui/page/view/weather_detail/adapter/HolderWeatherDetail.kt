package com.example.kotlinproject.ui.page.view.weather.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinproject.app_base.adapter.CallbackAdapter
import com.example.kotlinproject.constants.ConstAdapter
import com.example.kotlinproject.data.domain.weather.DtWeather
import com.example.kotlinproject.databinding.AdWeatherDetailBinding

class HolderWeatherDetail(val binding: AdWeatherDetailBinding, val callBack : CallbackAdapter) : RecyclerView.ViewHolder(binding.root) {
    init {
        binding.layerMain.setOnClickListener() {
            callBack.listClick(ConstAdapter.TYPE_ITEM_CLICK, binding.dataWeather as DtWeather)
        }
    }
}