package com.devika.ituneschallenge.injection.component

import android.content.Context
import com.devika.ituneschallenge.injection.module.DatabaseModule
import com.devika.ituneschallenge.injection.module.NetworkModule
import com.devika.ituneschallenge.injection.module.ViewModelModule
import com.devika.ituneschallenge.ui.SearchArtistFragment
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import javax.inject.Singleton

@Singleton
@Component(modules = [DatabaseModule::class,NetworkModule::class,ViewModelModule::class])
interface AppComponent {
     fun inject(searchArtistFragment: SearchArtistFragment)

    @Component.Factory
    interface Factory{
    fun create(@BindsInstance context: Context):AppComponent
    }
}