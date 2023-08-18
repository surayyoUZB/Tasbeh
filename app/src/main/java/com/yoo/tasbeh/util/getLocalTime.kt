package com.yoo.tasbeh.util

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
fun getLocalTime():String {
    return SimpleDateFormat("EEE,MMM dd", Locale.getDefault()).format(Date())
}