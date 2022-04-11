package cz.muni.log4ts.repository

import android.util.Log
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.Timestamp
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import cz.muni.log4ts.data.entities.LogEntry
import cz.muni.log4ts.data.ui.LogEntriesItem
import kotlinx.coroutines.tasks.await


/**
 * Log Repository for firebase cloud storage
 * Requires internet access
 */
class FirebaseLogRepository : LogRepositoryInterface {
    private val db = Firebase.firestore
    private val TAG = FirebaseLogRepository::class.simpleName;

    override suspend fun getLogEntriesItems(): List<LogEntriesItem> {
        val logEntries = getLogEntriesByUserId(1)
        return logEntries.map { it.toLogEntriesItem() }
    }

    override suspend fun getLogEntriesByUserId(userId: Long): List<LogEntry> {
        Log.d(FirebaseLogRepository::class.simpleName, String.format("Fetching userData from Firebase with userId %d", userId))
        val usersDataDocument :QuerySnapshot = getUserDataDocument(userId)
        Log.d(TAG, String.format("userData are fetched, content of documents is: %s", usersDataDocument.documents))
        val logEntriesFirebaseMap: List<MutableMap<String, Any>> =
            extractLogEntriesOfFirstUserFromUsersDataDocuments(usersDataDocument)
        Log.d(TAG, String.format("Raw logEntries : %s", logEntriesFirebaseMap))
        val logEntries = logEntriesFirebaseMap.map { makeLogEntryFromFirebaseDataMap(it) }
        Log.d(TAG, String.format("Parsed logEntries are: %s", logEntries))
        return logEntries
    }

    private fun extractLogEntriesOfFirstUserFromUsersDataDocuments(
        documents: QuerySnapshot
    ): List<MutableMap<String, Any>> {
        val userData = documents.documents[0].data
        return userData?.get("logEntries")!! as List<MutableMap<String, Any>>
    }

    private suspend fun getUserDataDocument(userId: Long): QuerySnapshot {
        return db.collection("usersData")
            .whereEqualTo("userId", userId)
            .limit(1)
            .get()
            .await()
    }

    private fun makeLogEntryFromFirebaseDataMap(logEntryData: MutableMap<String, Any>): LogEntry {
        return LogEntry(
            id = logEntryData["id"]!! as Long,
            name = logEntryData["name"]!! as String,
            namespace = logEntryData["namespace"]!! as String,
            project = logEntryData["project"]!! as String,
            startTime = logEntryData["startTime"]!! as Timestamp,
            endTime = logEntryData["endTime"]!! as Timestamp,
            loggedSeconds = logEntryData["loggedSeconds"]!! as Long,
        )
    }
}