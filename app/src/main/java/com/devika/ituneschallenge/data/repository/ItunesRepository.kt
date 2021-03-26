package com.devika.ituneschallenge.data.repository

import com.devika.ituneschallenge.data.database.ItunesDao
import com.devika.ituneschallenge.data.model.ArtistData
import com.devika.ituneschallenge.data.services.ApiService
import com.devika.ituneschallenge.utils.NetworkManager
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ItunesRepository @Inject constructor(
    private val apiService: ApiService,
    private val iTunesDao: ItunesDao,
    private val networkManager: NetworkManager
) {

    suspend fun getArtists(query: String): Flow<List<ArtistData>> {
        if (networkManager.isNetworkConnected()) {
            val response = apiService.getArtists(query)
            response.results.map { it.searchTerm = query }
            iTunesDao.addArtistsToDB(response.results)
        }
        return iTunesDao.getArtists(query)
    }
}