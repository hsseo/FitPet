package com.example.kotlinproject.data.model

import com.example.kotlinproject.app_base.BaseData
import com.example.kotlinproject.data.domain.city.DtCity
import com.example.kotlinproject.data.domain.weather.DtWeather
import com.example.kotlinproject.utils.UtilsDate
import com.example.kotlinproject.utils.UtilsLog
import java.util.*
import kotlin.math.max
import kotlin.math.min


class ResponseWeather : BaseData() {
    var cod = ""
    var message = 0
    var cnt = 0
    var list : ArrayList<DtWeather> = ArrayList()
    var city : DtCity = DtCity()


    val mapDt : HashMap<String, ArrayList<DtWeather>> = HashMap()

    private class SortDtWeatherTime : Comparator<DtWeather> {
        override fun compare(p0: DtWeather, p1: DtWeather): Int {
            return p0.dt.compareTo(p1.dt)
        }
    }

    fun getWeatherDatas() : ArrayList<DtWeather>{
        if(mapDt.size == 0) {
            //시간별로 데이터 정리.
            UtilsLog.e("map", "--------------------------------")
            for(data : DtWeather in list) {
                val keyDate : String = data.getDate()
                if(mapDt.containsKey(keyDate)) {
                    mapDt.get(keyDate)?.let {
                        it.add(data)
                    }
                } else {
                    val list : ArrayList<DtWeather> = ArrayList()
                    list.add(data)
                    mapDt.put(keyDate, list)
                }
            }
            UtilsLog.e("map", "--------------------------------")
        }

        val listDatas : ArrayList<DtWeather> = ArrayList()

        val strCurHHMM = UtilsDate.formatDate("HH:mm", System.currentTimeMillis())
        val curHHMM = UtilsDate.formatDate("HH:mm", strCurHHMM)
        for(keyDate : String in mapDt.keys) {
            var selDtWeather : DtWeather?= null

            //1. 시간별로 받은 데이터를 시간에서 가장 가까운 시간 데이터를 빼냄
            var time : Long = Long.MAX_VALUE

            //2. 하루의 Max / Min 온도 빼냄
            var maxTmp = Float.MIN_VALUE
            var minTmp = Float.MAX_VALUE

            val list = mapDt.get(keyDate)
            if(!list.isNullOrEmpty()) {
                for(dt : DtWeather in list) {
                    dt.city = city
                    maxTmp = max(dt.main.temp_max, maxTmp)
                    minTmp = min(dt.main.temp_min, minTmp)

                    val dtHourMin = UtilsDate.formatDate("HH:mm", UtilsDate.formatDate("yyyy-MM-dd HH:mm:ss", dt.dt_txt))
                    val dtTime = UtilsDate.formatDate("HH:mm", dtHourMin)
                    if(time > Math.abs(curHHMM - dtTime)) {
                        time = Math.abs(curHHMM - dtTime)
                        selDtWeather = dt
                    }
                }
            }

            selDtWeather?.let {
                it.main.temp_max = maxTmp
                it.main.temp_min = minTmp
                it.city = city
                it.weatherTime.clear()
                list?.let { selDtWeather.weatherTime.addAll(it) }
                listDatas.add(it)
            }
        }

        //Sort
        Collections.sort(listDatas, SortDtWeatherTime())
        return listDatas
    }
}