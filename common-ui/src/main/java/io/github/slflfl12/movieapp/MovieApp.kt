package io.github.slflfl12.movieapp

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp
import io.reactivex.plugins.RxJavaPlugins

@HiltAndroidApp
class MovieApp: Application() {
    override fun onCreate() {
        super.onCreate()
        RxJavaPlugins.setErrorHandler { Log.w("APP#", it) }
    }
}