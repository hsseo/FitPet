package com.example.kotlinproject.ui.page.view.weather

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.lifecycle.ViewModelProviders
import com.example.kotlinproject.app_base.BaseAct
import com.example.kotlinproject.app_base.BaseData
import com.example.kotlinproject.app_base.adapter.CallbackAdapter
import com.example.kotlinproject.constants.ConstAdapter
import com.example.kotlinproject.constants.ConstParam
import com.example.kotlinproject.data.DataManager
import com.example.kotlinproject.data.domain.weather.DtWeather
import com.example.kotlinproject.data.repository.weather.RepositoryWeather
import com.example.kotlinproject.databinding.ActivityWeatherBinding
import com.example.kotlinproject.ui.page.view.weather.adapter.AdapterWeather
import com.example.kotlinproject.ui.page.view.weather_detail.ActWeatherDetail

class ActWeather : BaseAct<ActivityWeatherBinding, WeatherViewModel>(), CallbackAdapter {

    var adapter: AdapterWeather? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        initObserveViwModel()
    }

    override fun createViewModel(): WeatherViewModel {
        val repository: RepositoryWeather = DataManager.get().getWeatherRepository()
        val introViewModelFactory = WeatherViewModelFactory(repository)
        return ViewModelProviders.of(this, introViewModelFactory)[WeatherViewModel::class.java]
    }

    override fun createViewBinding(layoutInflater: LayoutInflater): ActivityWeatherBinding {
        return ActivityWeatherBinding.inflate(layoutInflater)
    }

    fun init() {
        adapter = AdapterWeather(this).apply {
            this.vertical(true, binding.list)
            refreshWeather()
        }
    }

    override fun listRefresh() {
        refreshWeather()
    }

    override fun listMore(page: Int) {
    }

    override fun listClick(type: Int, data: BaseData) {
        if (type == ConstAdapter.TYPE_ITEM_CLICK) {
            val intent = Intent(applicationContext, ActWeatherDetail::class.java)
            intent.putExtra(ConstParam.PARAM_DATA, data as DtWeather)
            startActivity(intent)
        }
    }

    fun initObserveViwModel() {
        viewModel?.let {
            it.apiWeather.observe(this) {
            }
            it.weatherLiveData.observe(this) {
                binding.list.completeRefresh()
                adapter!!.add(it)
            }
            it.weatherErrorLiveData.observe(this) {
                binding.list.completeRefresh()
                binding.list.emptyMsg(it)
                adapter!!.clearDatas()
            }
            it.geoLiveData.observe(this) {}
        }
    }

    fun refreshWeather() {
        adapter?.let { it.clearDatas() }
        viewModel?.let { it.apiWeather() }
    }
}