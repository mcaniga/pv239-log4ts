package cz.muni.log4ts.ui.logEntries

import android.view.View
import cz.muni.log4ts.data.entities.LogEntry
import cz.muni.log4ts.repository.FirebaseLogRepository
import cz.muni.log4ts.ui.projects.ProjectsFragmentAction
import cz.muni.log4ts.util.ErrorHandler
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class LogEntriesViewHolderAction @Inject constructor() {
    @Inject
    lateinit var logRepository: FirebaseLogRepository

    val TAG = ProjectsFragmentAction::class.simpleName

    suspend fun deleteLogEntry(recyclerViewAdapter: LogEntriesRecyclerViewAdapter, logEntry: LogEntry, view: View) {
        try {
            logRepository.deleteLogEntry(logEntry.id, logEntry.userId)
            recyclerViewAdapter.deleteLogEntry(logEntry)
        } catch (e: Exception) {
            ErrorHandler.showErrorSnackbar(e, TAG, "Delete of log entry failed...", view)
        }
    }
}