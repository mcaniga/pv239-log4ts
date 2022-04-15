package cz.muni.log4ts.ui.projects

import android.util.Log
import android.view.View
import cz.muni.log4ts.data.entities.NewProject
import cz.muni.log4ts.extension.toProject
import cz.muni.log4ts.repository.FirebaseProjectRepository
import cz.muni.log4ts.util.ErrorHandler
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProjectsFragmentAction @Inject constructor() {
    @Inject
    lateinit var projectRepository: FirebaseProjectRepository

    val TAG = ProjectsFragmentAction::class.simpleName

    suspend fun addProject(recyclerViewAdapter: ProjectsRecyclerViewAdapter, newProject: NewProject, view: View) {
        try {
            val projectId: String =  projectRepository.addProjectInNamespace(newProject)
            val project = newProject.toProject(projectId)
            recyclerViewAdapter.addProject(project)
        } catch (e: Exception) {
            ErrorHandler.showErrorSnackbar(e, TAG, "Adding of project failed...", view)
        }
    }

    suspend fun getProjectsOrShowError(
        namespaceId: String,
        recyclerViewAdapter: ProjectsRecyclerViewAdapter,
        view: View
    ) {
        try {
            Log.d(TAG, "Getting projects...")
            val projectsItems = projectRepository.getAllProjectsInNamespace(namespaceId)
            Log.d(TAG, "Got projects, refreshing list...")
            recyclerViewAdapter.refreshList(projectsItems)
            Log.d(TAG, "Projects list was successfully refreshed")
        } catch (e: Exception) {
            ErrorHandler.showErrorSnackbar(e, TAG, "Fetching projects failed...", view)
        }
    }
}