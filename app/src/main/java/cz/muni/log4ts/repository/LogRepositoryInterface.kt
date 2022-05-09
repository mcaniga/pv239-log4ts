package cz.muni.log4ts.repository

import cz.muni.log4ts.data.entities.LogEntry
import cz.muni.log4ts.data.entities.NewLogEntry
import java.util.*

interface LogRepositoryInterface {
    suspend fun getLogEntriesByUserId(userId: String): List<LogEntry>
    suspend fun addLogEntry(logEntry: NewLogEntry): String
    suspend fun updateLogEntry(logEntry: LogEntry)
    suspend fun deleteLogEntry(id: String, userId: String)
    suspend fun getAllLogEntriesByUserByProject(userId: String, projectName: String, lowerBound: Date,
                                                upperBound: Date
    ): List<LogEntry>
}