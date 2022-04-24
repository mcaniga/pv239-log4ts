package cz.muni.log4ts.util

import android.graphics.Color.GREEN
import android.util.Log
import android.view.View
import androidx.navigation.NavController
import com.google.android.material.snackbar.Snackbar
import cz.muni.log4ts.ui.logEntries.LogEntriesFragmentDirections

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

        fun showActionWasSucessfullSnackbar(view: View?) {
            Log.e("StaticMethods", "Showing action was sucessfull in snackbar")
            val snack =
                view?.let { Snackbar.make(it, SUCCESSFUL_ACTION_TEXT, Snackbar.LENGTH_LONG) }
            snack?.setBackgroundTint(GREEN);
            snack?.show()
        }

        fun safelyNavigateUp(navController: NavController, tag: String?, view: View) {
            try {
                navController.navigateUp()
            } catch (e: Exception) {
                showErrorSnackbar(e, tag, "Cannot navigate up...", view)
            }
        }

        private const val SUCCESSFUL_ACTION_TEXT = "Action was successful";
    }
}