package com.example.kotlinproject.data.db.weather

import androidx.room.*
import com.example.kotlinproject.app_base.Apps
import com.example.kotlinproject.data.db.convertor.ConvertDtWeatherWeather

val DBNAME : String = "wheater_db"

@Database(entities = [DtWeatherDB::class], version = 1)
@TypeConverters(ConvertDtWeatherWeather::class)
abstract class DBWeather : RoomDatabase() {


    abstract fun dao() : DaoWeather

    companion object {
        var instance: DBWeather? = null
        fun get() : DBWeather {
            synchronized(this) {
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        Apps.context(),
                        DBWeather::class.java,
                        DBNAME
                    ).build()
                }
                return instance!!
            }
        }
    }
}