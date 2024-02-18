package com.srnyndrs.android.searchhub.data.util

import com.srnyndrs.android.searchhub.data.shared.Constants
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

// Converts API response string to Date
fun String.convertToDate(): Date? {
    val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH)
    return try { formatter.parse(this) } catch (e: Exception) { null }
}

// Formats date to string with 'yyyy.MM.dd. HH:mm' pattern
fun Date?.getDateTime(): String {
    val dateTimeFormatter = SimpleDateFormat("yyyy.MM.dd. HH:mm", Locale.ENGLISH)
    return this?.let { dateTimeFormatter.format(it) } ?: "----.--.--. --:--"
}

// Formats date to string with 'yyyy.MM.dd.' pattern
fun Date?.getDate(): String {
    val dateFormatter = SimpleDateFormat("yyyy.MM.dd.", Locale.ENGLISH)
    return this?.let { dateFormatter.format(it) } ?: "----.--.--"
}

// Formats date to string with 'HH:mm' pattern
fun Date?.getTime(): String {
    val timeFormatter = SimpleDateFormat("HH:mm", Locale.ENGLISH)
    return this?.let { timeFormatter.format(it) } ?: "--:--"
}

// Function to decode error codes
fun String?.decodeError(): String {
    val value = this.toString()
    return Constants.errorCodes.getOrDefault(value.trim(), value)
}