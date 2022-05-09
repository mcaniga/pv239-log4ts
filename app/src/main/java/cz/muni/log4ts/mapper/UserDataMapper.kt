package cz.muni.log4ts.mapper

import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot
import cz.muni.log4ts.data.entities.NewUser
import cz.muni.log4ts.data.entities.Project
import cz.muni.log4ts.data.entities.UserData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserDataMapper @Inject constructor() {
    fun makeNewUserDataFirebaseMap(user: FirebaseUser, username: String, useremail: String): Map<String, Any> =
        hashMapOf(
            "userId" to user.uid,
            "username" to username,
            "useremail" to useremail
        )

    fun getUsernameFromUserDataDocument(document: DocumentSnapshot): String =
        document.data?.get("username") as String

    fun makeUserDataFromUserDocument(
        userDocument: QuerySnapshot
    ): UserData {
        val data = userDocument.first().data
        return UserData(
            userId = data["userId"] as String,
            useremail = data["useremail"] as String,
            username = data["username"] as String,
            projects = data["projects"] as MutableList<String>
        )
    }

    fun makeFirebaseDataMapFromUserData(userData: UserData): Map<String, Any> {
        return hashMapOf(
                "projects" to userData.projects,
                "username" to userData.username,
                "useremail" to userData.useremail,
                "userId" to userData.userId
            )
    }

    fun makeProjectFromProjectDocument(
        namespaceId: String,
        projectDocument: DocumentSnapshot
    ): Project {
        val data = projectDocument.data
        return Project(
            id = projectDocument.id,
            namespaceId = namespaceId,
            name = data?.get("name")!! as String,
            users = data["users"]!! as MutableList<String>
        )
    }

    private fun getIdFromUserDataDocument(document: DocumentSnapshot): String =
        document.data?.get("id") as String

    fun getIdsFromUserDataDocuments(userDataDocuments: QuerySnapshot): List<String> {
        return userDataDocuments.documents.map { getIdFromUserDataDocument(it) }
    }

}