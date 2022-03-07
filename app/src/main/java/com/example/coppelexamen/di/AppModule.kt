package com.example.coppelexamen.di

import com.example.coppelexamen.common.Constantes
import com.example.coppelexamen.data.api.ApiService
import com.example.coppelexamen.data.respository.MarvelRepository
import com.example.coppelexamen.data.respository.MarvelServiceImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMarvelApi():ApiService{
        return Retrofit.Builder()
            .baseUrl(Constantes.HEROAPI_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(makeOkHttpClient())
            .build()
            .create(ApiService::class.java)
    }
    @Provides
    @Singleton
    fun provideMarvelRepository(api:ApiService):MarvelRepository{
        return MarvelServiceImp(api)
    }
    private fun makeOkHttpClient(): OkHttpClient {
        val cliente: OkHttpClient
            cliente = OkHttpClient.Builder()
                .addInterceptor(makeLogInterceptor())
                .build()
        return cliente
    }
    private fun makeLogInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return logging
    }
}