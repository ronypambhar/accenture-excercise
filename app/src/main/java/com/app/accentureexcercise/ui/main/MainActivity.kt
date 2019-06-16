package com.app.accentureexcercise.ui.main

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.accentureexcercise.R
import com.app.accentureexcercise.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast

class MainActivity : BaseActivity() {

    private lateinit var albumViewModel: AlbumViewModel
    private lateinit var adapterAlbum: AlbumAdapter

    private val viewModelProviderFactory: AlbumViewModelFactory by lazy {
        AlbumViewModelFactory(this, compositeDisposable)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        adapterAlbum = AlbumAdapter(R.layout.list_item_album, itemClick)
        rvAlbumList.run {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = adapterAlbum
        }
        albumViewModel = viewModelProviderFactory.create(AlbumViewModel::class.java)

        albumViewModel.getAlbumList().observe(this, Observer {
            adapterAlbum.addAll(ArrayList(it))
        })
    }

    private val itemClick = View.OnClickListener {
        when (it.id) {
            R.id.itemAlbum -> {
                val iPos = it.tag as Int
                toast(String.format(getString(R.string.album), adapterAlbum.list[iPos].title))
            }
        }
    }
}