package com.srnyndrs.android.searchhub.data.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun String.formatDate(): Pair<String, String> {
    val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH)
    val date = formatter.parse(this) ?: Date()
    val dateFormatter = SimpleDateFormat("yyyy.MM.dd", Locale.ENGLISH)
    val timeFormatter = SimpleDateFormat("HH:mm", Locale.ENGLISH)
    return date.let { Pair(dateFormatter.format(it).toString(),  timeFormatter.format(it).toString())}
}