package cz.muni.log4ts.ui.logEntries

import android.util.Log
import android.view.View
import cz.muni.log4ts.data.entities.NewLogEntry
import cz.muni.log4ts.extension.toLogEntry
import cz.muni.log4ts.repository.FirebaseLogRepository
import cz.muni.log4ts.util.ErrorHandler
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LogEntriesFragmentAction @Inject constructor() {
    @Inject
    lateinit var logRepository: FirebaseLogRepository

    val TAG = LogEntriesFragmentAction::class.simpleName

    suspend fun addLogEntry(recyclerViewAdapter: LogEntriesRecyclerViewAdapter, newLogEntry: NewLogEntry, view: View) {
        try {
            val logEntryId: String =  logRepository.addLogEntry(newLogEntry)
            val logEntry = newLogEntry.toLogEntry(logEntryId)
            recyclerViewAdapter.addLogEntry(logEntry)
        } catch (e: Exception) {
            ErrorHandler.showErrorSnackbar(e, TAG, "Adding of log entry failed...", view)
        }
    }

    suspend fun getLogEntriesOrShowError(
        userId: String,
        recyclerViewAdapter: LogEntriesRecyclerViewAdapter,
        view: View
    ) {
        try {
            Log.d(TAG, "Getting log entries items...")
            val logEntries = logRepository.getLogEntriesByUserId(userId)
            Log.d(TAG, "Got log entries items, refreshing list...")
            recyclerViewAdapter.refreshList(logEntries)
            Log.d(TAG, "Log entries list was successfully refreshed")
        } catch (e: Exception) {
            ErrorHandler.showErrorSnackbar(e, TAG, "Fetching log entries failed...", view)
        }
    }
}