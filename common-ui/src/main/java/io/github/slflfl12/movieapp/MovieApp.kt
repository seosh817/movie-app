package io.github.slflfl12.movieapp

import android.app.Application
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import dagger.hilt.android.HiltAndroidApp
import io.reactivex.plugins.RxJavaPlugins

@HiltAndroidApp
class MovieApp: Application() {
    override fun onCreate() {
        super.onCreate()
        RxJavaPlugins.setErrorHandler { Log.w("APP#", it) }
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }
}