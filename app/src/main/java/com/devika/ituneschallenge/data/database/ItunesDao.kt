package com.devika.ituneschallenge.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.devika.ituneschallenge.data.model.ArtistData
import kotlinx.coroutines.flow.Flow

@Dao
interface ItunesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addArtistsToDB(artistData: List<ArtistData>)

    @Query("select * from artists_data where searchTerm LIKE :query")
    fun getArtists(query: String): Flow<List<ArtistData>>

}