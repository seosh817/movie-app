package io.github.slflfl12.presentation.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel(){

    protected val compositeDisposable: CompositeDisposable = CompositeDisposable()

    fun unbindViewModel() {
        compositeDisposable.clear()
    }




}