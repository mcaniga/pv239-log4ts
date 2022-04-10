package cz.muni.log4ts.repository

import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import cz.muni.log4ts.data.entities.LogEntry
import cz.muni.log4ts.data.ui.LogEntriesItem
import kotlinx.coroutines.tasks.await
import java.lang.Exception

/**
 * Log Repository for firebase cloud storage
 * Requires internet access
 */
class FirebaseLogRepository : LogRepositoryInterface {
    private val db = Firebase.firestore

    override suspend fun getLogEntriesItems(): List<LogEntriesItem> {
        val logEntries = getLogEntriesByUserId(1)
        return logEntries.map { it.toLogEntriesItem() }
    }

    override suspend fun getLogEntriesByUserId(userId: Int): List<LogEntry> {
        try {
            val usersDataDocuments :QuerySnapshot = db.collection("usersData")
                .whereEqualTo("userId", userId)
                .limit(1)
                .get()
                .await()

            val logEntries: List<MutableMap<String, Any>> =
                extractLogEntriesOfFirstUserFromUsersDataDocuments(usersDataDocuments)
            return logEntries.map { makeLogEntryFromFirebaseDataMap(it) }
        } catch (e: Exception) {
            throw e // TODO: do something better than rethrow...
        }
    }

    private fun extractLogEntriesOfFirstUserFromUsersDataDocuments(
        documents: QuerySnapshot
    ): List<MutableMap<String, Any>> {
        val userData = documents.documents[0].data
        return userData?.get("logEntries")!! as List<MutableMap<String, Any>>
    }

    private fun makeLogEntryFromFirebaseDataMap(logEntryData: MutableMap<String, Any>): LogEntry {
        return LogEntry(
            id = logEntryData["id"]!! as Long,
            name = logEntryData["name"]!! as String,
            namespace = logEntryData["namespace"]!! as String,
            project = logEntryData["project"]!! as String,
            startTime = logEntryData["startTime"]!! as String,
            endTime = logEntryData["endTime"]!! as String,
            loggedSeconds = logEntryData["loggedSeconds"]!! as Long,
        )
    }
}