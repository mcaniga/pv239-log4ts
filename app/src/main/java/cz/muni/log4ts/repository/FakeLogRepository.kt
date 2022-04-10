package cz.muni.log4ts.repository

import cz.muni.log4ts.data.entities.LogEntry
import cz.muni.log4ts.data.ui.LogEntriesItem
import cz.muni.log4ts.util.getNowFormattedDateString

/**
 * Log Repository for manipulation with mocked log items
 * Can be used for testing the app in offline conditions
 */
class FakeLogRepository : LogRepositoryInterface {
    override fun getLogEntriesItems(): List<LogEntriesItem> {
        val logEntries = getLogEntriesByUserId(1)
        return logEntries.map { it.toLogEntriesItem() }
    }

    override fun getLogEntriesByUserId(userId: Int): List<LogEntry> {
        val count = 100
        val sixtyTwoMinutesInMillis: Int = 1000 * 60 * 62
        return mutableListOf<LogEntry>().apply {
            repeat(count) {
                val item = LogEntry(
                    id = it.toLong(),
                    name = "Log Entry - $it",
                    startTime = System.currentTimeMillis().minus(sixtyTwoMinutesInMillis)
                        .getNowFormattedDateString(),
                    endTime = System.currentTimeMillis().getNowFormattedDateString(),
                    loggedSeconds = 3720,
                    namespace = "piskaren",
                    project = "lidl"
                )
                add(item)
            }
        }
    }
}