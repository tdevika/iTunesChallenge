package com.devika.ituneschallenge.ui

import com.devika.ituneschallenge.data.model.ArtistData
import com.devika.ituneschallenge.data.repository.ItunesRepository
import com.devika.ituneschallenge.utils.Results
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SearchArtistUseCase @Inject constructor(private val iTunesRepository: ItunesRepository,
                                              private val dispatcher: CoroutineDispatcher) {

    suspend fun getArtistData(query: String): Flow<Results<List<ArtistData>>> =
        withContext(dispatcher) {
            iTunesRepository.getArtists(query)
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
