package com.example.applyhistory.util

import java.util.*

class DateAndTimeUtil {
    companion object {
        fun getCurrentPersianDate(): String? { //todo sample output 1400/4/24
            return try {
                val calendar = Calendar.getInstance()
                calendar.timeZone = TimeZone.getTimeZone("UTC")
                val year = calendar[Calendar.YEAR]
                val month = calendar[Calendar.MONTH]
                val day = calendar[Calendar.DAY_OF_MONTH]
                val jalaliToday = JalaliCalendar.gregorianToJalali(
                    JalaliCalendar.YearMonthDate(
                        year,
                        month,
                        day
                    )
                )
                jalaliToday.toString()
            } catch (e: java.lang.Exception) {
                ""
            }
        }
    }

}