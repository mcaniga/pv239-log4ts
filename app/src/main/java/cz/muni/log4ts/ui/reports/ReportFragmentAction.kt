package cz.muni.log4ts.ui.reports

import android.util.Log
import android.view.View
import cz.muni.log4ts.data.entities.NewProject
import cz.muni.log4ts.extension.toProject
import cz.muni.log4ts.repository.FirebaseLogRepository
import cz.muni.log4ts.repository.FirebaseProjectRepository
import cz.muni.log4ts.ui.projects.ProjectsRecyclerViewAdapter
import cz.muni.log4ts.util.ErrorHandler
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ReportFragmentAction @Inject constructor() {
    @Inject
    lateinit var projectRepository: FirebaseProjectRepository

    @Inject
    lateinit var logsRepository: FirebaseLogRepository

    val TAG = ReportFragmentAction::class.simpleName

    suspend fun getReportEntriesOrShowError(
        project: String,
        lowerBound: Date,
        upperBound: Date,
        recyclerViewAdapter: ReportRecyclerViewAdapter,
        view: View
    ) {
        try {
            Log.d(TAG, "Getting report entries...")
            val reportItems = logsRepository.getAllLogEntriesByProjectPerUser(project, lowerBound, upperBound)
            Log.d(TAG, "Got report entries, refreshing list...")
            recyclerViewAdapter.refreshList(reportItems)
            Log.d(TAG, "Projects list was successfully refreshed")
            recyclerViewAdapter.project = project
            recyclerViewAdapter.lowerBound = lowerBound
            recyclerViewAdapter.upperBound = upperBound

        } catch (e: Exception) {
            ErrorHandler.showErrorSnackbar(e, TAG, "Fetching projects failed...", view)
        }
    }
}