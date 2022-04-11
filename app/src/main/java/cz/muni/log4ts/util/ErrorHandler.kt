package cz.muni.log4ts.util

import android.util.Log
import android.view.View
import com.google.android.material.snackbar.Snackbar

class ErrorHandler {
    companion object StaticMethods {
        /**
         * Shows error snackbar and logs error message with ERROR priority
         * Should be used as a method for consistent error handling, without crashing the app
         * Can be useful in `catch` block
         */
        fun showErrorSnackbar(e: Exception, logTag: String?, errorMsg: String, view: View?) {
            e.message?.let { Log.e(logTag, it) }
            val snack =
                view?.let { Snackbar.make(it, errorMsg, Snackbar.LENGTH_LONG) }
            snack?.show()
        }
    }
}