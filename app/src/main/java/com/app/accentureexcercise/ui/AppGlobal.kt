package com.app.accentureexcercise.ui

import android.content.Context
import android.net.ConnectivityManager

class AppGlobal {
    companion object {
        fun isNetworkConnected(context: Context): Boolean {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork = cm.activeNetworkInfo
            return activeNetwork != null && activeNetwork.isConnectedOrConnecting
        }
    }
}
