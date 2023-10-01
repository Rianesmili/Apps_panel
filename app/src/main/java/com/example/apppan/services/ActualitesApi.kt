package com.example.apppan.services

import com.example.apppan.data.Actu
import retrofit2.Call
import retrofit2.http.GET

interface ActualitesApi {
    @GET("events")
    fun getActualites(): Call<List<Actu>>
}
