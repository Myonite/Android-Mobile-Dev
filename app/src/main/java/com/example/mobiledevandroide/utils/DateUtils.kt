package com.example.mobiledevandroide.utils

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

fun isValidDate(dateString: String): Boolean {
    return try {
        SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(dateString)
        true
    } catch (e: Exception) {
        false
    }
}

fun getCurrentDatabaseDateString(): String {
    val currentDate = Calendar.getInstance().time
    return SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(currentDate)
}