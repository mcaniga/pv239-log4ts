package cz.muni.log4ts.ui.auth.signout

import android.view.View
import androidx.navigation.NavController
import cz.muni.log4ts.dao.FirebaseAuthDao
import cz.muni.log4ts.ui.auth.register.RegisterFragmentDirections
import cz.muni.log4ts.ui.logEntries.LogEntriesFragmentDirections
import cz.muni.log4ts.ui.projects.ProjectsFragmentDirections
import cz.muni.log4ts.util.ErrorHandler
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SignOutFragmentAction @Inject constructor() {
    @Inject
    lateinit var authDao: FirebaseAuthDao

    private val TAG = SignOutFragmentAction::class.simpleName

    fun signOutFromLogEntries(navController: NavController, view: View) {
        try {
            authDao.signOut()
            // TODO: change after moving to other component
            navController.navigate(LogEntriesFragmentDirections.actionLogEntriesFragmentToLoginFragment())
        } catch (e: Exception) {
            ErrorHandler.showErrorSnackbar(e, TAG, "Sign out failed...", view)
        }
    }

    fun signOutFromProjects(navController: NavController, view: View) {
        try {
            authDao.signOut()
            // TODO: change after moving to other component
            navController.navigate(ProjectsFragmentDirections.actionProjectsFragmentToLoginFragment())
        } catch (e: Exception) {
            ErrorHandler.showErrorSnackbar(e, TAG, "Sign out failed...", view)
        }
    }
}