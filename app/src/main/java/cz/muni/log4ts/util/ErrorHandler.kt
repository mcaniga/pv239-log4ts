package cz.muni.log4ts.util

import android.graphics.Color.GREEN
import android.util.Log
import android.view.View
import androidx.navigation.NavController
import com.google.android.material.snackbar.Snackbar
import java.lang.String.format

class ErrorHandler {
    companion object StaticMethods {
        /**
         * Shows error snackbar and logs error message with ERROR priority
         * Should be used as a method for consistent error handling, without crashing the app
         * Can be useful in `catch` block
         */
        fun showErrorSnackbar(e: Exception, logTag: String?, errorMsg: String, view: View?) {
            e.message?.let { Log.e(logTag, it) }
            showBasicSnackbar(view, errorMsg)
        }

        fun showErrorSnackbar(errorMsg: String, view: View?) {
            showBasicSnackbar(view, errorMsg)
        }

        fun showOfflineSnackbar(view: View?) {
            showBasicSnackbar(view, APP_IS_OFFLINE_TEXT)
        }

        fun showActionWasSucessfullSnackbar(view: View?) {
            Log.e(TAG, SHOWING_ACTION_SUCCESSFUL_IN_SNACKBAR_TEXT)
            val snack = makeSnackbarSafely(view, SUCCESSFUL_ACTION_TEXT)
            snack?.setBackgroundTint(GREEN)
            snack?.show()
        }

        fun showActionWasSucessfullSnackbar(view: View?, text: String) {
            Log.e(TAG, SHOWING_ACTION_SUCCESSFUL_IN_SNACKBAR_TEXT)
            val snack = makeSnackbarSafely(view, text)
            snack?.setBackgroundTint(GREEN)
            snack?.show()
        }

        fun safelyNavigateUp(navController: NavController, tag: String?, view: View) {
            try {
                navController.navigateUp()
            } catch (e: Exception) {
                showErrorSnackbar(e, tag, CANNOT_NAVIGATE_UP_TEXT, view)
            }
        }

        private fun makeSnackbarSafely(
            view: View?,
            errorMsg: String
        ): Snackbar? {
            var snack: Snackbar? = null
            try {
                snack = view?.let { Snackbar.make(it, errorMsg, Snackbar.LENGTH_LONG) }
            } catch (e: Exception) {
                Log.d(TAG, format(CANNOT_DISPLAY_SNACKBAR_TEXT, e))
            }
            return snack
        }

        private fun showBasicSnackbar(view: View?, errorMsg: String) {
            var snack: Snackbar? = makeSnackbarSafely(view, errorMsg)
            snack?.show()
        }

        private const val SUCCESSFUL_ACTION_TEXT = "Action was successful"
        private const val TAG = "StaticMethods"
        private const val APP_IS_OFFLINE_TEXT = "App is offline"
        private const val CANNOT_DISPLAY_SNACKBAR_TEXT = "Cannot display snackbar: %s"
        private const val SHOWING_ACTION_SUCCESSFUL_IN_SNACKBAR_TEXT = "Showing action was sucessfull in snackbar"
        private const val CANNOT_NAVIGATE_UP_TEXT = "Cannot navigate up..."
    }
}