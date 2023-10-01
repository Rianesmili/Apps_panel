package com.example.apppan.setup

fun formatDate(date: Long): String {
    val currentDate = System.currentTimeMillis()
    val diff = currentDate - date

    return when {
        diff < 24 * 60 * 60 * 1000 -> "aujourd'hui"
        diff < 2 * 24 * 60 * 60 * 1000 -> "hier"
        else -> "il y a ${(diff / (24 * 60 * 60 * 1000))} jour(s)"
    }
}