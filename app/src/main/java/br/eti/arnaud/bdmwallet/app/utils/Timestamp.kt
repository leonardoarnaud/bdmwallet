package br.eti.arnaud.bdmwallet.app.utils

import android.text.format.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class Timestamp(private val value: Long) {

    fun getDate(): String {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = value * 1000L
        return SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(calendar.time)
    }

    fun getTime(): String {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = value * 1000L

        return SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(calendar.time)
    }

}