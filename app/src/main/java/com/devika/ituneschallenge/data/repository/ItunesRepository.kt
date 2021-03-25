package com.devika.ituneschallenge.data.repository

import com.devika.ituneschallenge.data.database.ItunesDao
import com.devika.ituneschallenge.data.model.ItunesData
import com.devika.ituneschallenge.data.services.ApiService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ItunesRepository @Inject constructor(private val apiService: ApiService, private val iTunesDao: ItunesDao) {

    suspend fun getItems(query: String): Flow<List<ItunesData>> {
        val itunesData = apiService.getItunesData(query)
        itunesData.results.map { it.searchTerm = query }
        iTunesDao.addItunesDataToDB(itunesData.results)
        return iTunesDao.getItunesData(query)
    }
}