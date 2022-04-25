package cz.muni.log4ts.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities.*

class NetworkResolver {
    companion object {
        private val networkTransports: List<Int> = arrayListOf(TRANSPORT_CELLULAR, TRANSPORT_WIFI, TRANSPORT_ETHERNET)
        const val TAG = "NetworkUtils"

        fun isOnline(context: Context): Boolean {
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager? ?: return false
            val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork) ?: return false
            return networkTransports.any { capabilities.hasTransport(it) }
        }
    }
}