package cz.muni.log4ts.ui.logEntries.newLogEntry

import android.view.View
import cz.muni.log4ts.data.entities.NewLogEntry
import cz.muni.log4ts.repository.FirebaseLogRepository
import cz.muni.log4ts.util.ErrorHandler
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewLogEntryFragmentAction @Inject constructor() {
    @Inject
    lateinit var logRepository: FirebaseLogRepository

    val TAG = NewLogEntryFragmentAction::class.simpleName

    suspend fun addLogEntry(newLogEntry: NewLogEntry, view: View) {
        try {
            logRepository.addLogEntry(newLogEntry)
            ErrorHandler.showActionWasSucessfullSnackbar(view)
        } catch (e: Exception) {
            ErrorHandler.showErrorSnackbar(e, TAG, "Adding of log entry failed...", view)
        }
    }
}