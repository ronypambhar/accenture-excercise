package com.app.accentureexcercise.ui.main

import android.app.Activity
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.app.accentureexcercise.R
import com.app.accentureexcercise.database.AlbumModel
import com.app.accentureexcercise.network.RequestInterface
import com.app.accentureexcercise.ui.AppGlobal
import com.app.accentureexcercise.ui.MyApp
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.toast

class AlbumViewModel(val activity: Activity, val disposable: CompositeDisposable) : ViewModel() {
    var alAlbums: LiveData<List<AlbumModel>>

    init {
        alAlbums = MyApp.getInstance().getDao().getAlbums()
    }

    val requestInterface: RequestInterface by lazy {
        RequestInterface.create()
    }


    fun getAlbumList(): LiveData<List<AlbumModel>> = alAlbums

    private fun loadAlbum() {
        if (alAlbums.value?.size == 0) {
            if (AppGlobal.isNetworkConnected(activity)) {
                disposable.add(
                    requestInterface.getAlbumList()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe({
                            Log.e("***", "Success: $it")
                            it.forEach { model ->
                                MyApp.getInstance().getDao().insertAlbum(model)
                            }
                            alAlbums = MyApp.getInstance().getDao().getAlbums()
                        }, {
                            Log.e("***", "Error: ${it.printStackTrace()}")
                        })
                )
            } else {
                activity.toast(activity.getString(R.string.no_internet))
            }
        }
    }
}