package com.example.coppelexamen.data.use_case

import com.example.coppelexamen.data.respository.MarvelRepository
import com.example.coppelexamen.utils.Resource
import com.example.coppelexamen.data.model.Character
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CharacterUserCase @Inject constructor(
    private val repository: MarvelRepository
){
    operator fun invoke(offset:Int):Flow<Resource<List<Character>>> = flow{
        try {
            emit(Resource.Loading<List<Character>>())
            val list = repository.getAllCharacter(offset=offset).data.results.map {
                it.toCaracter()
            }
            emit(Resource.Success<List<Character>>(list))
        }
        catch(e: HttpException) {
        emit(Resource.Error<List<Character>>(e.printStackTrace().toString()))
        }
        catch (e:IOException){
            emit(Resource.Error<List<Character>>(e.printStackTrace().toString()))
    }
    }

}