package cz.muni.log4ts.repository

import cz.muni.log4ts.data.entities.LogEntry
import cz.muni.log4ts.data.entities.NewLogEntry
import cz.muni.log4ts.data.ui.LogEntriesItem

interface LogRepositoryInterface {
    suspend fun getLogEntriesItems(userId: String): List<LogEntriesItem> // TODO: only for mocking, remove before merging of feature
    suspend fun getLogEntriesByUserId(userId: String): List<LogEntry>
    suspend fun addLogEntry(logEntry: NewLogEntry): String
    suspend fun updateLogEntry(logEntry: LogEntry)
    suspend fun deleteLogEntry(logEntry: LogEntry)
}