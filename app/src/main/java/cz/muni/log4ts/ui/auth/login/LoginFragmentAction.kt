package cz.muni.log4ts.ui.auth.login

import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import cz.muni.log4ts.dao.FirebaseAuthDao
import cz.muni.log4ts.data.entities.LoginUser
import cz.muni.log4ts.data.entities.NewUser
import cz.muni.log4ts.ui.auth.register.RegisterFragmentDirections
import cz.muni.log4ts.util.ErrorHandler
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginFragmentAction @Inject constructor() {
    @Inject
    lateinit var authDao: FirebaseAuthDao

    private val TAG = LoginFragmentAction::class.simpleName

    suspend fun loginUser(navController: NavController, loginUser: LoginUser, view: View) {
        try {
            authDao.signIn(loginUser)
            navController.navigate(LoginFragmentDirections.actionLoginFragmentToLogEntriesFragment())
        } catch (e: Exception) {
            ErrorHandler.showErrorSnackbar(e, TAG, e.message ?: "Sign in failed...", view)
        }
    }

    fun navigateToRegisterFragment(navController: NavController, view: View) {
        try {
            navController.navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
        }
        catch (e: Exception) {
            ErrorHandler.showErrorSnackbar(e, TAG, "Unable to navigate to register screen...", view)
        }
    }
}