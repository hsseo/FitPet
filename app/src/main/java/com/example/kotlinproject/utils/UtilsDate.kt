package com.example.kotlinproject.utils

import android.text.format.DateFormat
import com.example.kotlinproject.R
import com.example.kotlinproject.app_base.Apps
import java.text.SimpleDateFormat
import java.util.*

class UtilsDate {
    companion object {
        fun formatDate(format : String, time : Long) : String {
            var dateFormat = SimpleDateFormat(format, Locale.getDefault())
            return dateFormat.format(time)
        }
        fun formatDate(format : String, date : String) : Long {
            var dateFormat = SimpleDateFormat(format, Locale.getDefault())
            var dDate = dateFormat.parse(date)
            if(dDate != null) {
                return dDate.time
            }
            return 0
        }

        fun getFormattedDate(dtTxt: String): String {
            val smsTimeInMilis: Long = formatDate("yyyy-MM-dd HH:mm:ss", dtTxt)
            val smsTime = Calendar.getInstance()
            smsTime.timeInMillis = smsTimeInMilis
            val now = Calendar.getInstance()
            val dateTimeFormatString = Apps.context().getString(R.string.format_weather_date)
            return if (now[Calendar.DATE] == smsTime[Calendar.DATE]) {
                Apps.context().getString(R.string.str_today, DateFormat.format("HH:mm", smsTime).toString())
            } else if (now[Calendar.DATE] - smsTime[Calendar.DATE] == -1) {
                Apps.context().getString(R.string.str_tomorow, DateFormat.format("HH:mm", smsTime).toString())
            } else if (now[Calendar.YEAR] == smsTime[Calendar.YEAR]) {
                DateFormat.format(dateTimeFormatString, smsTime).toString()
            } else {
                DateFormat.format("MM dd yyyy, h:mm aa", smsTime).toString()
            }
        }
    }
}