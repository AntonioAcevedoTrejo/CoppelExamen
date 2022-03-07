package com.example.coppelexamen.data.respository

import com.example.coppelexamen.data.api.ApiService
import com.example.coppelexamen.data.model.HeroModel
import javax.inject.Inject

class MarvelServiceImp @Inject constructor(
    private val api: ApiService
):MarvelRepository{

    override suspend fun getAllCharacter(offset: Int): HeroModel {
        return api.getAllMarvel(offset = offset.toString())
    }
}