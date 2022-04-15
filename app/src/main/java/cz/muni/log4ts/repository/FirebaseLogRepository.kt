package cz.muni.log4ts.repository

import android.util.Log
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.QuerySnapshot
import cz.muni.log4ts.dao.FirebaseLogDao
import cz.muni.log4ts.data.entities.LogEntry
import cz.muni.log4ts.data.entities.NewLogEntry
import cz.muni.log4ts.data.ui.LogEntriesItem
import cz.muni.log4ts.extension.toLogEntriesItem
import cz.muni.log4ts.mapper.LogEntryMapper
import okhttp3.internal.format

// TODO: use DI container
/**
 * Log Repository for firebase cloud storage
 * Requires internet access
 */
class FirebaseLogRepository : LogRepositoryInterface {
    private val dao = FirebaseLogDao()
    private val mapper = LogEntryMapper()
    private val TAG = FirebaseLogRepository::class.simpleName;

    override suspend fun getLogEntriesItems(): List<LogEntriesItem> {
        val logEntries = getLogEntriesByUserId("1")
        return logEntries.map { it.toLogEntriesItem() }
    }

    override suspend fun getLogEntriesByUserId(userId: String): List<LogEntry> {
        Log.d(TAG, String.format("Fetching userData from Firebase with userId %s", userId))
        val userLogEntriesDocuments: QuerySnapshot = dao.getUserLogEntriesDocuments(userId)
        return mapper.makeLogEntriesFromLogEntriesDocuments(userId, userLogEntriesDocuments)
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

    override suspend fun deleteLogEntry(logEntry: LogEntry) {
        Log.d(
            TAG, String.format(
                "Deleting log entry %s to userData in Firebase with userId %s", logEntry, logEntry.userId
            )
        )
        dao.deleteLogEntryDocument(logEntry)
        Log.d(TAG, "Successfully deleted the log entry")
    }
}
