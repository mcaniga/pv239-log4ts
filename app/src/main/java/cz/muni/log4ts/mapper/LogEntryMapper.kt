package cz.muni.log4ts.mapper

import android.os.Build
import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import cz.muni.log4ts.data.entities.LogEntry
import cz.muni.log4ts.data.entities.NewLogEntry
import cz.muni.log4ts.data.entities.ReportEntry
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.format.DateTimeFormatter
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LogEntryMapper @Inject constructor() {
    fun makeLogEntriesFromLogEntriesDocuments(userId: String, userLogEntriesDocuments: QuerySnapshot): List<LogEntry> {
        return userLogEntriesDocuments.documents.map { makeLogEntryFromLogEntryDocument(userId, it) }
    }

    fun makeLogEntriesFromLogEntriesDocuments(userId: String, userLogEntriesDocuments: List<DocumentSnapshot>): List<LogEntry> {
        return userLogEntriesDocuments.map { makeLogEntryFromLogEntryDocument(userId, it) }
    }

    private fun makeLogEntryFromLogEntryDocument(
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

    fun makeReportEntriesFromLogEntriesDocuments(userId: String, userReportEntriesDocuments: QuerySnapshot): List<ReportEntry> {
        return userReportEntriesDocuments.documents.map { makeReportEntryFromLogEntryDocument(it, userId) }
    }

    fun makeReportEntriesFromLogEntriesDocuments(userId: String, userReportEntriesDocuments: List<DocumentSnapshot>): List<ReportEntry> {
        return userReportEntriesDocuments.map { makeReportEntryFromLogEntryDocument(it, userId) }
    }

    fun makeReportEntriesFromLogEntries(userId: String, username: String, userReportEntriesDocuments: List<LogEntry>): List<ReportEntry> {
        return userReportEntriesDocuments.map { makeReportEntryFromLogEntry(it, userId, username) }
    }

    private fun makeReportEntryFromLogEntry(
        logEntry: LogEntry,
        userId: String,
        username: String
    ): ReportEntry {
        return ReportEntry(
            seconds = logEntry.loggedSeconds,
            username = username,
            userId = userId
        )
    }

    private fun makeReportEntryFromLogEntryDocument(
        logEntryDocument: DocumentSnapshot,
        userId: String
    ): ReportEntry {
        val data = logEntryDocument.data
        return ReportEntry(
            seconds = data?.get("loggedSeconds")!! as Long,
            username = data["name"]!! as String,
            userId = userId
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

    fun makeFirebaseStringDataMapFromLogEntry(logEntry: LogEntry, formatter: SimpleDateFormat): Map<String, String> =
        hashMapOf(
            "userId" to logEntry.userId,
            "id" to logEntry.id,
            "project" to logEntry.project,
            "namespace" to logEntry.namespace,
            "startTime" to  formatter.format(logEntry.startTime.toDate()),
            "endTime" to formatter.format(logEntry.endTime.toDate()),
            "name" to logEntry.name,
            "loggedSeconds" to logEntry.loggedSeconds.toString()
        )
}