package cz.muni.log4ts.mapper

import com.google.firebase.auth.FirebaseUser
import cz.muni.log4ts.data.entities.NewUser
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserDataMapper @Inject constructor() {
    fun makeNewUserDataFirebaseMap(user: FirebaseUser, username: String): Map<String, Any> =
        hashMapOf(
            "userId" to user.uid,
            "username" to username
        )
}