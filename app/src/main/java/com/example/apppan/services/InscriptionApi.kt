package com.example.apppan.services

import com.example.apppan.data.InscriptionReponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface InscriptionApi {

    @Multipart
    @POST("/authentication/register")
    suspend fun register(
        @Part("name") name: RequestBody,
        @Part("email") email: RequestBody,
        @Part("phone") phone: RequestBody,
        @Part picture: MultipartBody.Part? // Ceci est optionnel et doit être géré en conséquence
    ): Response<InscriptionReponse>
}