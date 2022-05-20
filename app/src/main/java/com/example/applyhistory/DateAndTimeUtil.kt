package com.example.applyhistory

import java.text.SimpleDateFormat
import java.util.*

class DateAndTimeUtil {
companion object {
    fun getCurrentPersianTime(): String? { //todo sample  output 16:30:46
        return try {
            val now = Date()
            var sdfDate = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH)
            val strDate = sdfDate.format(now)
            val date = sdfDate.parse(strDate)
            sdfDate = SimpleDateFormat("HH:mm:ss", Locale.ENGLISH)
            val time = sdfDate.format(date!!)
            time
        } catch (e: java.lang.Exception) {
            ""
        }
    }
}

}