package com.devika.ituneschallenge.injection.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.devika.ituneschallenge.injection.scope.ViewModelScope
import com.devika.ituneschallenge.ui.SearchArtistViewModel
import com.devika.ituneschallenge.utils.ItunesViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ItunesViewModelFactory):ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelScope(SearchArtistViewModel::class)
    abstract fun addSearchArtistViewModel(searchArtistViewModel: SearchArtistViewModel):ViewModel
}