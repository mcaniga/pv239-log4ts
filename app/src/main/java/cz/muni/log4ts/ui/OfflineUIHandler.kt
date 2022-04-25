package cz.muni.log4ts.ui

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.Button
import cz.muni.log4ts.util.ErrorHandler
import cz.muni.log4ts.util.NetworkResolver
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OfflineUIHandler @Inject constructor() {
    fun enableButtonIfOnline(context: Context, button: Button, buttonName: String) {
        val isAppOnline = NetworkResolver.isOnline(context)
        Log.d(NetworkResolver.TAG, getAppIsOnlineLogMessage(isAppOnline, buttonName))
        button.isEnabled = isAppOnline
    }

    fun showOfflineSnackbarIfOffline(context: Context, view: View) {
        val isAppOffline = !NetworkResolver.isOnline(context)
        Log.d(NetworkResolver.TAG, getAppIsOfflineLogMessage(isAppOffline))
        if (isAppOffline) {
            ErrorHandler.showOfflineSnackbar(view)
        }
    }

    private fun getAppIsOnlineLogMessage(isAppOnline: Boolean, buttonName: String): String {
        if (isAppOnline) {
            return java.lang.String.format("App is online, enabling %s button", buttonName)
        }
        return java.lang.String.format("App is offline, disabling %s button", buttonName)
    }

    private fun getAppIsOfflineLogMessage(isAppOffline: Boolean): String {
        if (isAppOffline) {
            return "App is offline"
        }
        return "App is online"
    }
}