package com.devika.ituneschallenge

import android.app.Application
import com.devika.ituneschallenge.injection.component.DaggerAppComponent

class ItunesApplication : Application() {
    val appComponent by lazy {
        DaggerAppComponent.factory().create(this)
    }
}