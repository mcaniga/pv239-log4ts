package cz.muni.log4ts.dao

import android.util.Log
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import cz.muni.log4ts.data.entities.LoginUser
import cz.muni.log4ts.data.entities.NewUser
import cz.muni.log4ts.mapper.UserDataMapper
import kotlinx.coroutines.tasks.await
import java.lang.String.format
import javax.inject.Inject

class FirebaseAuthDao @Inject constructor() {
    @Inject
    lateinit var firebaseUserDataDao: FirebaseUserDataDao

    @Inject
    lateinit var userDataMapper: UserDataMapper

    private val auth = Firebase.auth
    private val TAG = FirebaseAuthDao::class.simpleName

    fun getCurrentUser(): FirebaseUser? {
        val currentUser = auth.currentUser
        Log.d(TAG, format("Got current user %s", currentUser ?: "null"))
        return currentUser
    }

    fun getCurrentUserId(): String? {
        val currentUser = getCurrentUser()
        return currentUser?.uid
    }

    suspend fun createUser(newUser: NewUser) {
        Log.d(TAG, format("Creating user from data %s", newUser))
        val user = auth.createUserWithEmailAndPassword(newUser.email, newUser.password).await().user
        Log.d(TAG, "User created successfully")
        val data = user?.let { userDataMapper.makeNewUserDataFirebaseMap(it, newUser.username, newUser.email) }
        Log.d(TAG, "Made initial user data")
        user?.let { firebaseUserDataDao.createUserData(it.uid, data!!) }
        Log.d(TAG, "Initial user data created successfully")
    }

    suspend fun signIn(newUser: LoginUser) {
        Log.d(TAG, format("Creating user from data %s", newUser))
        val user = auth.signInWithEmailAndPassword(newUser.email, newUser.password).await().user
        user?.let { Log.d(TAG, format("User with id %s created successfully", it.uid)) }
    }

    fun signOut() {
        auth.signOut()
    }
}