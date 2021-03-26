package com.devika.ituneschallenge.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey


data class Response(
    val resultCount: Int,
    val results: List<ArtistData>
)

@Entity(tableName = "artists_data")
data class ArtistData(
    val artworkUrl100: String,
    @PrimaryKey val collectionId: Int,
    val artistName: String?,
    var searchTerm: String = ""
)

