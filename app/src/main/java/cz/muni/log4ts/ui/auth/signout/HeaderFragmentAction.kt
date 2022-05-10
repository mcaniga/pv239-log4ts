package cz.muni.log4ts.ui.auth.signout

import android.view.View
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import cz.muni.log4ts.dao.FirebaseAuthDao
import cz.muni.log4ts.ui.logEntries.LogEntriesFragmentDirections
import cz.muni.log4ts.ui.projects.ProjectsFragmentDirections
import cz.muni.log4ts.util.ErrorHandler
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HeaderFragmentAction @Inject constructor() {
    @Inject
    lateinit var authDao: FirebaseAuthDao

    private val TAG = HeaderFragmentAction::class.simpleName

    fun signOut(navController: NavController, view: View, signOutDirections: NavDirections) {
        try {
            authDao.signOut()
            navController.navigate(signOutDirections)
        } catch (e: Exception) {
            ErrorHandler.showErrorSnackbar(e, TAG, "Sign out failed...", view)
        }
    }
}