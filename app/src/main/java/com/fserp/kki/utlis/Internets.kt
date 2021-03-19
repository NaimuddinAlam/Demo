package com.fserp.kki.utlis

import android.content.Context
import android.net.ConnectivityManager

class Internets {
    companion object {
        fun hasNetworkAccess(context: Context): Boolean {
            val conection = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            try {
                val networkInfo = conection.activeNetworkInfo
                return networkInfo != null && networkInfo.isConnectedOrConnecting

            } catch (e: Exception) {
                return false
            }
        }

    }


}