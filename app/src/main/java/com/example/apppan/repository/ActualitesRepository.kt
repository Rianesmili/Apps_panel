package com.example.apppan.repository

import androidx.lifecycle.MutableLiveData
import com.example.apppan.data.Actu
import com.example.apppan.services.ActualitesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ActualitesRepository {

    val actualitesLiveData: MutableLiveData<List<Actu>?> = MutableLiveData()
    val isLoading: MutableLiveData<Boolean> = MutableLiveData(false)

    private val client = OkHttpClient.Builder().addInterceptor { chain ->
        val request: Request = chain.request().newBuilder()
            .addHeader("X-AP-Key", "uD4Muli8nO6nzkSlsNM3d1Pm")
            .addHeader("X-AP-DeviceUID", "trial")
            .addHeader("Accept", "application/json")
            .build()
        chain.proceed(request)
    }.build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://test-pgt-dev.apnl.ws/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    private val actualitesApi: ActualitesApi = retrofit.create(ActualitesApi::class.java)


    suspend fun fetchActualites() {
        isLoading.postValue(true)
        withContext(Dispatchers.IO) {
            val response = actualitesApi.getActualites().execute()
            val actualites = response.body()?.sortedByDescending { it.publishedAt }
            actualitesLiveData.postValue(actualites)
        }
        isLoading.postValue(false)
    }
}