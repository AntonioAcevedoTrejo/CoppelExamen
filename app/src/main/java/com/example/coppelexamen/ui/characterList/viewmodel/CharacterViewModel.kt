package com.example.coppelexamen.ui.characterList.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coppelexamen.data.use_case.CharacterUserCase
import com.example.coppelexamen.ui.characterList.MarvelListState
import com.example.coppelexamen.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val characterUseCase: CharacterUserCase
):ViewModel() {

    private val marvelValue = MutableStateFlow(MarvelListState())
    var _marvelValue : StateFlow<MarvelListState> = marvelValue
    val response = MutableLiveData<MarvelListState>()

    fun getAllCharactersData(offset: Int)= viewModelScope.launch(Dispatchers.IO) {
        characterUseCase(offset=offset).collect {
            when(it){
                is Resource.Success->{
                    marvelValue.value = MarvelListState(charecterList = it.data?: emptyList())
                    response.postValue(MarvelListState(charecterList = it.data?: emptyList()))
                    Log.i("TAGS","success: "+marvelValue.value.toString())
                }
                is Resource.Loading->{
                    marvelValue.value = MarvelListState(isLoading = true)
                    response.postValue(MarvelListState(isLoading = true))
                    Log.i("TAGS","loading: "+marvelValue.value.toString())
                }
                is Resource.Error ->{
                    marvelValue.value = MarvelListState(error = it.message?:"Error de servicio consulta el log")
                    response.postValue(MarvelListState(error = it.message?:"Error vuelve a intentar"))
                    Log.i("TAGS","Error: "+marvelValue.value.toString())
                }
            }
        }
    }
}