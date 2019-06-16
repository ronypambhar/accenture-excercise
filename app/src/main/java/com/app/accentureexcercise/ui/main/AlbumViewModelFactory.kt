package com.app.accentureexcercise.ui.main

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.reactivex.disposables.CompositeDisposable

class AlbumViewModelFactory(val activity: Activity, val disposable: CompositeDisposable) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        AlbumViewModel(activity, disposable) as T
}