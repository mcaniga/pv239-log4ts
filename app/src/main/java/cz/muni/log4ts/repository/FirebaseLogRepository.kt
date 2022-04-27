package cz.muni.log4ts.repository

import android.util.Log
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.QuerySnapshot
import cz.muni.log4ts.dao.FirebaseLogDao
import cz.muni.log4ts.data.entities.LogEntry
import cz.muni.log4ts.data.entities.NewLogEntry
import cz.muni.log4ts.mapper.LogEntryMapper
import java.lang.String.format
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Log Repository for firebase cloud storage
 * Requires internet access
 */
@Singleton
class FirebaseLogRepository @Inject constructor() : LogRepositoryInterface {
    @Inject
    lateinit var dao: FirebaseLogDao
    @Inject
    lateinit var mapper: LogEntryMapper

    private val TAG = FirebaseLogRepository::class.simpleName;

    override suspend fun getLogEntriesByUserId(userId: String): List<LogEntry> {
        Log.d(TAG, String.format("Fetching userData from Firebase with userId %s", userId))
        val userLogEntriesDocuments: QuerySnapshot = dao.getUserLogEntriesDocuments(userId)
        Log.d(
            TAG,
            String.format(
                "logEntries documents for user are fetched, content of documents is: %s",
                userLogEntriesDocuments.documents
            )
        )
        val logEntries = mapper.makeLogEntriesFromLogEntriesDocuments(userId, userLogEntriesDocuments)
        Log.d(TAG, String.format("Parsed logEntries are: %s", logEntries))
        return logEntries
    }

    override suspend fun addLogEntry(logEntry: NewLogEntry): String {
        Log.d(
            TAG, String.format(
                "Adding log entry %s to userData in Firebase with userId %s", logEntry, logEntry.userId
            )
        )
        val data: Map<String, Any> = mapper.makeFirebaseDataMapFromNewLogEntry(logEntry)
        Log.d(TAG, String.format("Made firebase data map: %s from given log entry", data))
        val logEntryDocument: DocumentReference = dao.addLogEntryToUserDataDocument(logEntry.userId, data)
        Log.d(TAG, format("Successfully added the log entry, id: %s", logEntryDocument.id))
        return logEntryDocument.id
    }

    override suspend fun updateLogEntry(logEntry: LogEntry) {
        val data: Map<String, Any> = mapper.makeFirebaseDataMapFromLogEntry(logEntry)
        Log.d(TAG, String.format("Made firebase data map: %s from given log entry", data))
        dao.updateUserLogEntriesDocuments(logEntry.userId, logEntry.id, data)
        Log.d(TAG, "Successfully updated the log entry")
    }

    override suspend fun deleteLogEntry(id: String, userId: String) {
        Log.d(
            TAG, String.format(
                "Deleting log entry %s to userData in Firebase with userId %s", id, userId
            )
        )
        dao.deleteLogEntryDocument(id, userId)
        Log.d(TAG, "Successfully deleted the log entry")
    }
}
