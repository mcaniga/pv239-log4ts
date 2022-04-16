package cz.muni.log4ts.repository

import cz.muni.log4ts.data.entities.LogEntry
import cz.muni.log4ts.data.entities.NewLogEntry

interface LogRepositoryInterface {
    suspend fun getLogEntriesByUserId(userId: String): List<LogEntry>
    suspend fun addLogEntry(logEntry: NewLogEntry): String
    suspend fun updateLogEntry(logEntry: LogEntry)
    suspend fun deleteLogEntry(id: String, userId: String)
}