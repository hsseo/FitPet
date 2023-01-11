package com.example.kotlinproject.data.db.weather

import androidx.room.*

@Dao
interface DaoWeather {
    @Query(/* value = */ "SELECT * FROM weather_db")
    fun all() : List<DtWeatherDB>

    @Insert
    fun add(vararg dt : DtWeatherDB)

    @Query(/* value = */ "DELETE FROM weather_db")
    fun deleteWeathers()
}