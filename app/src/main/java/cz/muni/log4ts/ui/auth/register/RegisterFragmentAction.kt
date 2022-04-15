package cz.muni.log4ts.ui.auth.register

import android.view.View
import androidx.navigation.NavController
import cz.muni.log4ts.dao.FirebaseAuthDao
import cz.muni.log4ts.data.entities.NewUser
import cz.muni.log4ts.util.ErrorHandler
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RegisterFragmentAction @Inject constructor() {
    @Inject
    lateinit var authDao: FirebaseAuthDao

    private val TAG = RegisterFragmentAction::class.simpleName

    suspend fun registerUser(navController: NavController, newUser: NewUser, view: View) {
        try {
            authDao.createUser(newUser)
            navController.navigate(RegisterFragmentDirections.actionRegisterFragmentToLogEntriesFragment())
        } catch (e: Exception) {
            ErrorHandler.showErrorSnackbar(e, TAG, "Register failed...", view)
        }
    }
}