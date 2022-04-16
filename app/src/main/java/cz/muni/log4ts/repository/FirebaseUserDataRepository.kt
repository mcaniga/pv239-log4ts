package cz.muni.log4ts.repository

import android.util.Log
import com.google.firebase.firestore.DocumentSnapshot
import cz.muni.log4ts.dao.FirebaseAuthDao
import cz.muni.log4ts.dao.FirebaseUserDataDao
import cz.muni.log4ts.mapper.UserDataMapper
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
}