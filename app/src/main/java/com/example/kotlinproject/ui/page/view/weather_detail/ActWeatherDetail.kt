package com.example.kotlinproject.ui.page.view.weather_detail

import android.os.Bundle
import android.view.LayoutInflater
import androidx.lifecycle.ViewModelProviders
import com.example.kotlinproject.app_base.BaseAct
import com.example.kotlinproject.app_base.BaseData
import com.example.kotlinproject.app_base.adapter.CallbackAdapter
import com.example.kotlinproject.constants.ConstParam
import com.example.kotlinproject.data.domain.weather.DtWeather
import com.example.kotlinproject.databinding.ActivityWeatherDetailBinding
import com.example.kotlinproject.ui.page.view.weather.adapter.AdapterWeatherDetail

class ActWeatherDetail : BaseAct<ActivityWeatherDetailBinding, WeatherDetailViewModel>(),
    CallbackAdapter {

    var adapter: AdapterWeatherDetail? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        initObserveViwModel()
    }

    override fun createViewModel(): WeatherDetailViewModel {
        val weathers: DtWeather = intent.getSerializableExtra(ConstParam.PARAM_DATA) as DtWeather
        val introViewModelFactory = WeatherDetailViewModelFactory(weathers)
        return ViewModelProviders.of(this, introViewModelFactory)
            .get(WeatherDetailViewModel::class.java)
    }

    override fun createViewBinding(layoutInflater: LayoutInflater): ActivityWeatherDetailBinding {
        return ActivityWeatherDetailBinding.inflate(layoutInflater)
    }

    fun init() {
        adapter = AdapterWeatherDetail(this)
        adapter?.let { it.vertical(false, binding.list) }
        viewModel?.let { it.loadWeathers() }
    }

    override fun listRefresh() {
    }

    override fun listMore(page: Int) {
    }

    override fun listClick(type: Int, data: BaseData) {
    }

    fun initObserveViwModel() {
        viewModel?.let {
            it.weathersLiveData.observe(this) {
                adapter!!.add(it)
            }
        }
    }
}