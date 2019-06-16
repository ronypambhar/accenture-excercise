package com.app.accentureexcercise.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.disposables.CompositeDisposable

abstract class BaseActivity : AppCompatActivity() {

    val compositeDisposable: CompositeDisposable by lazy {
        CompositeDisposable()
    }
    /*val viewModelProviderFactory: ViewModelProvider.AndroidViewModelFactory by lazy {
        ViewModelProvider.AndroidViewModelFactory.getInstance(application)
    }*/

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
    }
}