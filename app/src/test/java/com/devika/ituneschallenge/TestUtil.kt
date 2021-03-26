package com.devika.ituneschallenge

import com.devika.ituneschallenge.data.model.ArtistData

object TestUtil {
    val ARTIST = ArtistData("abc", 1000, "Rain")
    val ARTISTS =
            object : ArrayList<ArtistData>() {
                init {
                    add(ARTIST)
                    add(ArtistData("abc1", 1000, "Rain"))
                    add(ArtistData("abc2", 1000, "main"))
                }
            }
}
