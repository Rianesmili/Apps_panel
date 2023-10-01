package com.example.apppan.di

import com.example.apppan.repository.InfosRepository
import com.example.apppan.repository.InscriptionRepository
import com.example.apppan.services.InscriptionApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object Module {

    @Provides
    fun provideInscriptionApi(): InscriptionApi {
        return Retrofit.Builder()
            .baseUrl("https://test-pgt-dev.apnl.ws/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(InscriptionApi::class.java)
    }

    @Provides
    fun provideInscriptionRepository(inscriptionApi: InscriptionApi): InscriptionRepository {
        return InscriptionRepository(inscriptionApi)
    }

    @Provides
    fun providesInfosRepository(): InfosRepository = InfosRepository()
}
