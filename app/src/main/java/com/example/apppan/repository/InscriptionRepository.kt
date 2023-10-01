package com.example.apppan.repository

import com.example.apppan.data.Inscription
import com.example.apppan.data.InscriptionReponse
import com.example.apppan.services.InscriptionApi
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import javax.inject.Inject

class InscriptionRepository @Inject constructor(
private val inscriptionApi: InscriptionApi
) {
    suspend fun registerUser(registrationModel: Inscription): InscriptionReponse{
        val nameRequestBody = RequestBody.create("text/plain".toMediaTypeOrNull(), registrationModel.name)
        val emailRequestBody = RequestBody.create("text/plain".toMediaTypeOrNull(), registrationModel.email)
        val phoneRequestBody = RequestBody.create("text/plain".toMediaTypeOrNull(), registrationModel.phone)

        val response = inscriptionApi.register(nameRequestBody, emailRequestBody, phoneRequestBody, null)

        val successModel = response.body()?.success ?: ""

        return InscriptionReponse(successModel)
    }
}