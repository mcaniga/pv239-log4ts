package cz.muni.log4ts.repository

import android.util.Log
import com.google.firebase.firestore.DocumentSnapshot
import cz.muni.log4ts.dao.FirebaseAuthDao
import cz.muni.log4ts.dao.FirebaseUserDataDao
import cz.muni.log4ts.data.entities.Project
import cz.muni.log4ts.data.entities.UserData
import cz.muni.log4ts.exceptions.UserNotFound
import cz.muni.log4ts.mapper.UserDataMapper
import java.lang.IllegalArgumentException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirebaseUserDataRepository @Inject constructor() : UserDataRepositoryInterface {
    @Inject
    lateinit var dao: FirebaseUserDataDao

    @Inject
    lateinit var authDao:  FirebaseAuthDao

    @Inject
    lateinit var mapper: UserDataMapper

    private val TAG = FirebaseUserDataRepository::class.simpleName;

    override suspend fun getCurrentUsername(): String {
        val currentUserId = authDao.getCurrentUserId()!!
        Log.d(
            TAG,
            String.format("Fetching username from Firebase for user with id %s", currentUserId)
        )
        val userDocument: DocumentSnapshot = dao.getUserDataDocument(currentUserId)
        Log.d(
            TAG, String.format(
                "User data document is fetched, content is: %s",
                userDocument.data
            )
        )
        val username = mapper.getUsernameFromUserDataDocument(userDocument)
        Log.d(TAG, String.format("Parsed username is: %s", username))
        return username
    }

    suspend fun getUserDataDocumentByEmail(email: String): UserData {
        val userDocument = dao.getUserDataDocumentByEmail(email)
        if (userDocument.isEmpty) {
            throw UserNotFound("No user found")
        }
        return mapper.makeUserDataFromUserDocument(userDocument)
    }

    override suspend fun updateUserDataDocument(userData: UserData) {
        val data: Map<String, Any> = mapper.makeFirebaseDataMapFromUserData(userData)
        Log.d(TAG, String.format("Made firebase data map: %s from given project", data))
        dao.updateUserData(userData.userId, data)
        Log.d(TAG, "Successfully updated the project")
    }


}