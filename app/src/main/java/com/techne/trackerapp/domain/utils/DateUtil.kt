package com.techne.trackerapp.domain.utils

import java.text.SimpleDateFormat
import java.util.Date


fun getTime(): String {
    val currentDate = Date()
    val dateFormat = SimpleDateFormat("yy/MM")
    return dateFormat.format(currentDate)
}

