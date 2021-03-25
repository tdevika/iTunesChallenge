package com.devika.ituneschallenge.data.domain

sealed class UiState {
    data class Success(val value:Any):UiState()
    data class Error(val message:String):UiState()
    object Progress:UiState()
}

inline fun <reified T> Any.getList(): List<T>? {
    if(this is List<*>?){
        return filterIsInstance<T>()
    }
    return null
}