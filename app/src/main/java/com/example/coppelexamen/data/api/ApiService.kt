package com.example.coppelexamen.data.api

import com.example.coppelexamen.common.Constantes
import com.example.coppelexamen.data.model.HeroModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/v1/public/characters")
    suspend fun getAllMarvel(
        @Query("apikey")apikey:String = Constantes.API_KEY,
        @Query("ts") ts:String = Constantes.timeStamp,
        @Query("hash")hash:String = Constantes.hash(),
        @Query("offset") offset:String
    ): HeroModel

}