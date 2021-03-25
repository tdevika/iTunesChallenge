package com.devika.ituneschallenge.ui

import com.devika.ituneschallenge.data.model.ItunesData
import com.devika.ituneschallenge.data.repository.ItunesRepository
import com.devika.ituneschallenge.utils.Results
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEmpty
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SearchArtistUseCase @Inject constructor(private val iTunesRepository: ItunesRepository) {

    suspend fun getArtistData(artist: String): Flow<Results<List<ItunesData>>> =
        withContext(Dispatchers.IO) {
            iTunesRepository.getItems(artist)
                .map {
                    when (it.isEmpty()) {
                        true -> Results.Empty
                        false -> Results.Success(it)
                    }
                }
                .onStart {
                   emit(Results.Progress)
                }
                .catch { e ->
                    e.message?.let { Results.Error(it) }
                }
        }
}
