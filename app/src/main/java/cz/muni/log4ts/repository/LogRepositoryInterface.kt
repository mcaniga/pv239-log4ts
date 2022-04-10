package cz.muni.log4ts.repository

import cz.muni.log4ts.data.entities.LogEntry
import cz.muni.log4ts.data.ui.LogEntriesItem

// TODO: add implementation: FirebaseLogRepository
interface LogRepositoryInterface {
    suspend fun getLogEntriesItems(): List<LogEntriesItem> // TODO: only for mocking, remove before merging of feature
    suspend fun getLogEntriesByUserId(userId: Int): List<LogEntry>
}