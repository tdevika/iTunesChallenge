package com.devika.ituneschallenge.data.services

import com.devika.ituneschallenge.data.model.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("search")
   suspend fun getItunesData(@Query("term") artist:String): Response
}