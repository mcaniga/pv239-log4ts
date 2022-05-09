package cz.muni.log4ts.ui.reports.detail

import android.util.Log
import android.view.View
import cz.muni.log4ts.repository.FirebaseLogRepository
import cz.muni.log4ts.repository.FirebaseProjectRepository
import cz.muni.log4ts.util.ErrorHandler
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ReportDetailFragmentAction @Inject constructor() {
    @Inject
    lateinit var projectRepository: FirebaseProjectRepository

    @Inject
    lateinit var logsRepository: FirebaseLogRepository

    val TAG = ReportDetailFragmentAction::class.simpleName

    suspend fun getReportDetailsEntriesOrShowError(
        userId: String,
        project: String,
        lowerBound: Date,
        upperBound: Date,
        recyclerViewAdapter: ReportDetailRecyclerViewAdapter,
        view: View
    ) {
        try {
            Log.d(TAG, "Getting report entries for userid: $userId and project: $project ...")
            val logEntries = logsRepository.getAllLogEntriesByUserByProject(
                userId,
                project,
                lowerBound,
                upperBound
            )
            Log.d(TAG, "Got report entries, refreshing list...")
            recyclerViewAdapter.refreshList(logEntries)
            Log.d(TAG, "Projects list was successfully refreshed")
        } catch (e: Exception) {
            ErrorHandler.showErrorSnackbar(e, TAG, "Fetching projects failed...", view)
        }
    }
}