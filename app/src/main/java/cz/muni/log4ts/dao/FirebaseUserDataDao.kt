package cz.muni.log4ts.dao

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirebaseUserDataDao @Inject constructor() {
    private val db = Firebase.firestore

    suspend fun createUserData(userId: String, data: Map<String, Any>) {
        db
            .collection("usersData").document(userId)
            .set(data)
            .await()
    }
}