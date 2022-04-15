package cz.muni.log4ts.ui.logEntries

import android.util.Log
import android.view.View
import cz.muni.log4ts.data.entities.NewLogEntry
import cz.muni.log4ts.extension.toLogEntriesItem
import cz.muni.log4ts.extension.toLogEntry
import cz.muni.log4ts.repository.FirebaseLogRepository
import cz.muni.log4ts.repository.LogRepositoryInterface
import cz.muni.log4ts.util.ErrorHandler

class LogEntriesFragmentAction {
    // TODO: use DI
    private val logRepository: LogRepositoryInterface by lazy {
        FirebaseLogRepository()
    }

    val TAG = LogEntriesFragmentAction::class.simpleName

    suspend fun addLogEntry(recyclerViewAdapter: LogEntriesRecyclerViewAdapter, newLogEntry: NewLogEntry) {
        val logEntryId: String =  logRepository.addLogEntry(newLogEntry)
        val logEntry = newLogEntry.toLogEntry(logEntryId)
        recyclerViewAdapter.addLogEntry(logEntry.toLogEntriesItem())
    }

    suspend fun getLogEntriesOrShowError(
        recyclerViewAdapter: LogEntriesRecyclerViewAdapter,
        view: View
    ) {
        try {
            Log.d(TAG, "Getting log entries items...")
            val logEntriesItems = logRepository.getLogEntriesItems()
            Log.d(TAG, "Got log entries items, refreshing list...")
            recyclerViewAdapter.refreshList(logEntriesItems)
            Log.d(TAG, "Log entries list was successfully refreshed")
        } catch (e: Exception) {
            ErrorHandler.showErrorSnackbar(e, TAG, "Fetching user data failed...", view)
        }
    }
}