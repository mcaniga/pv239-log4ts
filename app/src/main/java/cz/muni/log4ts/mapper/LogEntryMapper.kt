package cz.muni.log4ts.mapper

import android.util.Log
import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import cz.muni.log4ts.data.entities.LogEntry
import cz.muni.log4ts.data.entities.NewLogEntry

class LogEntryMapper {
    val TAG = LogEntryMapper::class.simpleName

    fun makeLogEntriesFromLogEntriesDocuments(userId: String, userLogEntriesDocuments: QuerySnapshot): List<LogEntry> {
        Log.d(
            TAG,
            String.format(
                "logEntries documents for user are fetched, content of documents is: %s",
                userLogEntriesDocuments.documents
            )
        )
        val logEntries = userLogEntriesDocuments.documents.map { makeLogEntryFromLogEntryDocument(userId, it) }
        Log.d(TAG, String.format("Parsed logEntries are: %s", logEntries))
        return logEntries
    }

    fun makeLogEntryFromLogEntryDocument(
        userId: String,
        logEntryDocument: DocumentSnapshot
    ): LogEntry {
        val data = logEntryDocument.data
        return LogEntry(
            id = logEntryDocument.id,
            userId = userId,
            name = data?.get("name")!! as String,
            namespace = data["namespace"]!! as String,
            project = data["project"]!! as String,
            startTime = data["startTime"]!! as Timestamp,
            endTime = data["endTime"]!! as Timestamp,
            loggedSeconds = data["loggedSeconds"]!! as Long,
        )
    }

    fun makeFirebaseDataMapFromNewLogEntry(newLogEntry: NewLogEntry): Map<String, Any> =
        hashMapOf(
            "name" to newLogEntry.name,
            "namespace" to newLogEntry.namespace,
            "project" to newLogEntry.project,
            "startTime" to newLogEntry.startTime,
            "endTime" to newLogEntry.endTime,
            "loggedSeconds" to newLogEntry.loggedSeconds
        )

    fun makeFirebaseDataMapFromLogEntry(logEntry: LogEntry): Map<String, Any> =
        hashMapOf(
            "name" to logEntry.name,
            "namespace" to logEntry.namespace,
            "project" to logEntry.project,
            "startTime" to logEntry.startTime,
            "endTime" to logEntry.endTime,
            "loggedSeconds" to logEntry.loggedSeconds
        )
}