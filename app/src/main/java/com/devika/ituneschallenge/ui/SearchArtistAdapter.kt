package com.devika.ituneschallenge.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.devika.ituneschallenge.data.model.ArtistData
import com.devika.ituneschallenge.databinding.ItemArtistsBinding
import javax.inject.Inject

class SearchArtistAdapter @Inject constructor() :

    ListAdapter<ArtistData, SearchArtistAdapter.SearchArtistViewModel>(
        ARTIST_DIFF_UTIL
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchArtistViewModel {
        val binding = ItemArtistsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchArtistViewModel(binding)
    }

    override fun onBindViewHolder(holder: SearchArtistViewModel, position: Int) {
        holder.setData(getItem(position))
    }

    inner class SearchArtistViewModel(private val binding: ItemArtistsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setData(item: ArtistData?) {
            binding.artistData = item
            Glide.with(binding.root.context)
                .load(item?.artworkUrl100)
                .into(binding.artWork);

        }
    }

}

private val ARTIST_DIFF_UTIL: DiffUtil.ItemCallback<ArtistData> =
    object : DiffUtil.ItemCallback<ArtistData>() {

        override fun areItemsTheSame(oldItem: ArtistData, newItem: ArtistData): Boolean =
            oldItem.collectionId == newItem.collectionId

        override fun areContentsTheSame(oldItem: ArtistData, newItem: ArtistData): Boolean =
            oldItem == newItem
    }