package cz.muni.log4ts.repository

import com.google.firebase.Timestamp
import cz.muni.log4ts.data.entities.LogEntry
import cz.muni.log4ts.data.ui.LogEntriesItem
import cz.muni.log4ts.util.subtractSeconds

/**
 * Log Repository for manipulation with mocked log items
 * Can be used for testing the app in offline conditions
 */
class FakeLogRepository : LogRepositoryInterface {
    override suspend fun getLogEntriesItems(): List<LogEntriesItem> {
        val logEntries = getLogEntriesByUserId(1)
        return logEntries.map { it.toLogEntriesItem() }
    }

    override suspend fun getLogEntriesByUserId(userId: Long): List<LogEntry> {
        val count = 100
        val now = Timestamp.now();
        val sixtyTwoMinutesInSeconds: Long = 1000 * 62
        return mutableListOf<LogEntry>().apply {
            repeat(count) {
                val item = LogEntry(
                    id = it.toLong(),
                    name = "Log Entry - $it",
                    startTime = now.subtractSeconds(sixtyTwoMinutesInSeconds),
                    endTime = now,
                    loggedSeconds = 3720,
                    namespace = "piskaren",
                    project = "lidl"
                )
                add(item)
            }
        }
    }
}