package com.app.accentureexcercise.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


abstract class BaseAdapter<T>(val layout: Int = 0) : RecyclerView.Adapter<BaseAdapter.ViewHolder>() {

    val TAG = javaClass.simpleName
    val list = ArrayList<T>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(LayoutInflater.from(parent.context).inflate(layout, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int, payloads: MutableList<Any>) {
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
        } else {
            onBind(holder.view, position, list[position], payloads)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        onBind(holder.view, position, list[position], mutableListOf())
    }


    override fun getItemId(position: Int) = position.toLong()

    override fun getItemCount(): Int = list.size


    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    fun addAll(dataList: ArrayList<T>) {
        list.clear()
        list.addAll(dataList)
        notifyDataSetChanged()
    }

    fun getAll(): ArrayList<T> {
        return list
    }

    fun getItem(position: Int): T {
        return list[position]
    }

    abstract fun onBind(view: View, position: Int, item: T, payloads: MutableList<Any>)
}
