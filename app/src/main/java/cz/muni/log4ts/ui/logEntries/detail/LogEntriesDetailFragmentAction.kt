package cz.muni.log4ts.ui.logEntries.detail

import android.view.View
import cz.muni.log4ts.data.entities.LogEntry
import cz.muni.log4ts.repository.FirebaseLogRepository
import cz.muni.log4ts.util.ErrorHandler
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LogEntriesDetailFragmentAction @Inject constructor() {
    @Inject
    lateinit var logRepository: FirebaseLogRepository

    val TAG = LogEntriesDetailFragmentAction::class.simpleName

    suspend fun editLogEntry(logEntry: LogEntry, view: View) {
        try {
            logRepository.updateLogEntry(logEntry)
        } catch (e: Exception) {
            ErrorHandler.showErrorSnackbar(e, TAG, "Editing of log entry failed...", view)
        }
    }
}