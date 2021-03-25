package com.devika.ituneschallenge.utils

sealed class Results<out T> {
    data class Success<out T>(val value:T):Results<T>()
    data class Error(val message:String):Results<Nothing>()
    object Progress:Results<Nothing>()
    object Empty:Results<Nothing>()
}