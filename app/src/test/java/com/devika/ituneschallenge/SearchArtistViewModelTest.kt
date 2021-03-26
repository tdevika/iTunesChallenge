package com.devika.ituneschallenge

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.devika.ituneschallenge.data.repository.ItunesRepository
import com.devika.ituneschallenge.ui.SearchArtistUseCase
import com.devika.ituneschallenge.ui.SearchArtistViewModel
import com.devika.ituneschallenge.utils.Results
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import java.lang.Math.random

@ExperimentalCoroutinesApi
class SearchArtistViewModelTest {

    // system under test
    lateinit var searchArtistViewModel: SearchArtistViewModel
    lateinit var searchArtistUseCase: SearchArtistUseCase

    private val testDispatcher = TestCoroutineDispatcher()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    lateinit var itunesRepository: ItunesRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        searchArtistUseCase = SearchArtistUseCase(itunesRepository, testDispatcher)
        searchArtistViewModel = SearchArtistViewModel(searchArtistUseCase)
    }

    @Test
    fun `flow emits successfully`() = runBlocking {
        stubArtists()
        val flowData = searchArtistUseCase.getArtistData("Refain")

        // Verify
        flowData.collect {
            if (it is Results.Success) assertEquals(it.value,TestUtil.ARTISTS)
        }
    }

    private suspend fun stubArtists() {
        `when`(itunesRepository.getArtists(random().toString())).thenReturn(
            flow {
                emit(TestUtil.ARTISTS)
            }
        )
    }
}