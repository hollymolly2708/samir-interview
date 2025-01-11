package com.android.samir_interview.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object Utilities {
    fun formatTanggal(tanggal: String?): String? {
        var output: String? = null
        if (tanggal != null) {
            val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale("id", "ID"))
            val outputFormat = SimpleDateFormat("dd MMMM yyyy", Locale("id", "ID"))
            val date = inputFormat.parse(tanggal)
            output = outputFormat.format(date as Date)
        }
        return output

    }
}