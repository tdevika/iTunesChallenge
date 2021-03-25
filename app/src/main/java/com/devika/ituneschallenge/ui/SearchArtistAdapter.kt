package com.devika.ituneschallenge.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.devika.ituneschallenge.data.model.ItunesData
import com.devika.ituneschallenge.databinding.ItemArtistsBinding
import javax.inject.Inject

class SearchArtistAdapter @Inject constructor() :
    ListAdapter<ItunesData, SearchArtistAdapter.SearchArtistViewModel>(
        ITUNES_DIFF_UTIL
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchArtistViewModel {
        val binding = ItemArtistsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchArtistViewModel(binding)
    }

    override fun onBindViewHolder(holder: SearchArtistViewModel, position: Int) {
        holder.setData(getItem(position))
    }

    inner class SearchArtistViewModel(val binding: ItemArtistsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setData(item: ItunesData?) {
            binding.artistData = item
            Glide.with(binding.root.context)
                .load(item?.artworkUrl100)
                .into(binding.artWork);

        }
    }

}

private val ITUNES_DIFF_UTIL: DiffUtil.ItemCallback<ItunesData> =
    object : DiffUtil.ItemCallback<ItunesData>() {

        override fun areItemsTheSame(oldItem: ItunesData, newItem: ItunesData): Boolean =
            oldItem.collectionId == newItem.collectionId

        override fun areContentsTheSame(oldItem: ItunesData, newItem: ItunesData): Boolean =
            oldItem == newItem
    }