package com.devika.ituneschallenge.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey


data class Response(
    val resultCount: Int,
    val results: List<ItunesData>
)

@Entity(tableName = "itunes_data")
data class ItunesData(
    val artworkUrl100: String,
    @PrimaryKey val collectionId: Int,
    val artistName: String?,
    var searchTerm: String = ""
)

