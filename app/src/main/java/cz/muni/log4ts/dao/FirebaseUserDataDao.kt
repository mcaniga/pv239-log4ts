package cz.muni.log4ts.dao

import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
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

    suspend fun updateUserData(userId: String, data: Map<String, Any>) {
        db
            .collection("usersData").document(userId)
            .set(data)
            .await()
    }

    suspend fun getUserDataDocument(userId: String): DocumentSnapshot {
        return db
            .collection("usersData").document(userId)
            .get()
            .await()
    }

    suspend fun getUserDataDocumentByEmail(useremail: String): QuerySnapshot {
        return db
            .collection("usersData")
            .whereEqualTo("useremail", useremail)
            .get()
            .await()
    }

    suspend fun getUserDocuments(projectName: String): QuerySnapshot {
        db.collectionGroup("logEntries").whereEqualTo("name", projectName)
        return db.collection("usersData")
            .get()
            .await()
    }
}