package cz.muni.log4ts.dao

import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import cz.muni.log4ts.data.dto.SimpleUserDto
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

    suspend fun getAllLogEntriesDocuments(userIds: List<String>): QuerySnapshot {
        return db
            .collection("usersData")
            .whereIn("id", userIds)

            .get()
            .await()
    }

    suspend fun getUserIdsByProject(userId: String, projectName: String): QuerySnapshot {
        return db
            .collection("usersData")
            .document(userId)
            .collection("logEntries")
            .whereEqualTo("project", projectName)
            .get()
            .await()
    }

    suspend fun getAllLogEntriesDocuments(projectName: String): QuerySnapshot {
        return db
            .collectionGroup("logEntries")
            .whereEqualTo("name", projectName)
            .get()
            .await()

    }

    suspend fun getAllUserIds(): List<String> {
        return db.collection("usersData")
            .get()
            .await().documents.map { doc -> doc.id }
    }

    suspend fun getAllUserDtos(): List<SimpleUserDto> {
        return db.collection("usersData")
            .get()
            .await()
            .documents.map { getUserDto(it) }
    }

    private fun getUserDto(document: DocumentSnapshot): SimpleUserDto {
        val userData = document.data
        return SimpleUserDto(userId = userData!!["userId"] as String,
            username = userData["username"] as String )
    }

    suspend fun getUserLogEntriesDocuments(
        userId: String,
        projectName: String,
        lowerBound: Timestamp,
        upperBound: Timestamp
    ): QuerySnapshot {
        val res = db
            .collection("usersData")
            .document(userId)
            .collection("logEntries")
            .whereEqualTo("project", projectName)
            .whereGreaterThanOrEqualTo("startTime", lowerBound)
//            .whereLessThanOrEqualTo("endTime", upperBound)
            .get()
            .await()
        return res
    }


}