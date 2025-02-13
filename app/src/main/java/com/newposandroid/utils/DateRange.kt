package com.newposandroid.utils

import java.util.Calendar

object DateRange {

    fun getDateRange(range: String): HashMap<String,Long> {
        val startDateCalendar = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }
        val endDateCalendar = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 23)
            set(Calendar.MINUTE, 59)
            set(Calendar.SECOND, 59)
            set(Calendar.MILLISECOND, 999)
        }
        when(range) {
            "Today" -> {

            }
            "Yesterday" -> {
                startDateCalendar.add(Calendar.DAY_OF_YEAR, -1)
            }
            "Last week" -> {
                startDateCalendar.add(Calendar.DAY_OF_YEAR, -7)
            }
            "Last month" -> {
                startDateCalendar.add(Calendar.MONTH, -1)
            }
        }
        val startDate = startDateCalendar.timeInMillis
        val endDate = endDateCalendar.timeInMillis
        val hashMap = hashMapOf<String,Long>()
        hashMap["startDate"] = startDate
        hashMap["endDate"] = endDate
        return hashMap
    }
}