package com.udacity.asteroidradar

import java.text.SimpleDateFormat
import java.util.*

fun main() {
    println(getDay(5).toString("yyyy-MM-dd"))
}

fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
    val formatter = SimpleDateFormat(format, locale)
    return formatter.format(this)
}

fun getDay(day: Int): Date {
    val calendar = Calendar.getInstance()
    calendar.add(Calendar.DAY_OF_YEAR, day)

    return calendar.time
}