package com.example.coppelexamen.ui.characterList

import com.example.coppelexamen.data.model.Character

data class MarvelListState(
    val isLoading:Boolean =false,
    val charecterList:List<Character> = emptyList(),
    val error: String = ""
)