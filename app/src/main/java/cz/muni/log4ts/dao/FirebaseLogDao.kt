package cz.muni.log4ts.dao

import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import cz.muni.log4ts.data.entities.LogEntry
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirebaseLogDao @Inject constructor() {
    private val db = Firebase.firestore

    suspend fun getUserLogEntriesDocuments(userId: String): QuerySnapshot {
        return db
            .collection("usersData").document(userId)
            .collection("logEntries")
            .get()
            .await()
    }

    suspend fun addLogEntryToUserDataDocument(
        userId: String,
        data: Map<String, Any>
    ): DocumentReference {
        return db
            .collection("usersData").document(userId)
            .collection("logEntries")
            .add(data)
            .await()
    }

    suspend fun updateUserLogEntriesDocuments(userId: String, id: String, data: Map<String, Any>) {
        db
            .collection("usersData").document(userId)
            .collection("logEntries").document(id)
            .set(data)
            .await()
    }

    suspend fun deleteLogEntryDocument(id: String, userId: String) {
        db
            .collection("usersData").document(userId)
            .collection("logEntries").document(id)
            .delete()
            .await()
    }
}