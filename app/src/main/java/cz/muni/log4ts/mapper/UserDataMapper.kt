package cz.muni.log4ts.mapper

import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.DocumentSnapshot
import cz.muni.log4ts.data.entities.NewUser
import cz.muni.log4ts.data.entities.Project
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserDataMapper @Inject constructor() {
    fun makeNewUserDataFirebaseMap(user: FirebaseUser, username: String): Map<String, Any> =
        hashMapOf(
            "userId" to user.uid,
            "username" to username
        )

    fun getUsernameFromUserDataDocument(document: DocumentSnapshot): String =
        document.data?.get("username") as String

    fun makeProjectFromProjectDocument(
        namespaceId: String,
        projectDocument: DocumentSnapshot
    ): Project {
        val data = projectDocument.data
        return Project(
            id = projectDocument.id,
            namespaceId = namespaceId,
            name = data?.get("name")!! as String,
        )
    }
}