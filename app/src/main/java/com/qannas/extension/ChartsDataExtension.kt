package com.qannas.extension

import com.github.mikephil.charting.data.Entry

val weeklyDays = arrayOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")
val monthlyDays = arrayOf("1 Sep", "5 Sep", "10 Sep", "15 Sep", "20 Sep", "25 Sep", "30 Sep")
val dailyHours = arrayOf("4", "8", "12", "16", "20", "24")
val yearlyMonths = arrayOf("Jan", "Mar", "May", "Jul", "Sep", "Nov")
val allData = arrayOf("2023", "2021", "2019", "2017", "2015", "2013", "2011")
const val ONE_DAY = 0
const val ONE_WEEK = 1
const val ONE_MONTH = 2
const val ONE_YEAR = 3
const val ALL_TYPE = 4

fun getDailyHoursData() : ArrayList<Entry> {
    val values1 = ArrayList<Entry>()
    values1.add(Entry(1f, -4f))
    values1.add(Entry(2f, -2f))
    values1.add(Entry(2.5f, 5f))
    values1.add(Entry(4f, -4.5f))
    values1.add(Entry(5f, 4f))
    values1.add(Entry(6f, 0f))
    values1.add(Entry(7f, -3f))
    return values1
}

fun getWeeklyData() : ArrayList<Entry> {
    val values1 = ArrayList<Entry>()
    values1.add(Entry(1f, -2f))
    values1.add(Entry(2f, 1f))
    values1.add(Entry(2.5f, -4f))
    values1.add(Entry(4f, 4.5f))
    values1.add(Entry(5f, 1f))
    values1.add(Entry(6f, 3f))
    values1.add(Entry(7f, -2f))
    values1.add(Entry(8f, -3.4f))
    return values1
}

fun getMonthlyData() : ArrayList<Entry> {
    val values1 = ArrayList<Entry>()
    values1.add(Entry(1f, 3f))
    values1.add(Entry(2f, 0f))
    values1.add(Entry(2.5f, 4f))
    values1.add(Entry(4f, -4.5f))
    values1.add(Entry(5f, 3f))
    values1.add(Entry(6f, 0f))
    values1.add(Entry(7f, -2f))
    values1.add(Entry(8f, 3.4f))
    return values1
}


fun getAllData() : ArrayList<Entry> {
    val values1 = ArrayList<Entry>()
    values1.add(Entry(1f, 4f))
    values1.add(Entry(2f, 0f))
    values1.add(Entry(2.5f, -2f))
    values1.add(Entry(4f, -4.5f))
    values1.add(Entry(5f, 2f))
    values1.add(Entry(6f, 4.8f))
    values1.add(Entry(7f, -2f))
    values1.add(Entry(8f, 0.3f))
    return values1
}


fun getYearlyData() : ArrayList<Entry> {
    val values1 = ArrayList<Entry>()
    values1.add(Entry(1f, 0f))
    values1.add(Entry(2f, -2f))
    values1.add(Entry(2.5f, -5f))
    values1.add(Entry(4f, -2f))
    values1.add(Entry(5f, 0.3f))
    values1.add(Entry(6f, 2f))
    values1.add(Entry(7f, -3f))
    return values1
}