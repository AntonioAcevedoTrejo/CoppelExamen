package com.example.coppelexamen.data.respository

import com.example.coppelexamen.data.model.HeroModel

interface MarvelRepository {
suspend fun getAllCharacter(offset: Int): HeroModel
}