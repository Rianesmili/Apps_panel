package com.example.apppan.data

data class Actu(
    val id: Long = 0L,
    val title: String,
    val description: String,
    val latitude: Double,
    val longitude: Double,
    val pictureUrl: String,
    val publishedAt: Long
)
