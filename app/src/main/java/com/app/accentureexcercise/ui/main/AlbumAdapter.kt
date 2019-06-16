package com.app.accentureexcercise.ui.main

import android.view.View
import com.app.accentureexcercise.R
import com.app.accentureexcercise.base.BaseAdapter
import com.app.accentureexcercise.database.AlbumModel
import kotlinx.android.synthetic.main.list_item_album.view.*

class AlbumAdapter(layout: Int, val itemClick: View.OnClickListener) : BaseAdapter<AlbumModel>(layout) {
    override fun onBind(view: View, position: Int, item: AlbumModel, payloads: MutableList<Any>) {
        view.run {
            itemAlbum.tag = position
            itemAlbum.setOnClickListener(itemClick)
            tvUserId.text = String.format(context.getString(R.string.user_id), item.userId)
            tvAlbumId.text = String.format(context.getString(R.string.album_id), item.id)
            tvTitle.text = String.format(context.getString(R.string.album), item.title)
        }
    }
}