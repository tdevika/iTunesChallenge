package com.devika.ituneschallenge.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.devika.ituneschallenge.data.model.ItunesData
import kotlinx.coroutines.flow.Flow

@Dao
interface ItunesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addItunesDataToDB(itunesData: List<ItunesData>)

    @Query("select * from itunes_data where searchTerm LIKE :query")
    fun getItunesData(query: String): Flow<List<ItunesData>>

}