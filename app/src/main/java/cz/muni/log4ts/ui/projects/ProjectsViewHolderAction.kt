package cz.muni.log4ts.ui.projects

import android.view.View
import cz.muni.log4ts.data.entities.Project
import cz.muni.log4ts.repository.FirebaseProjectRepository
import cz.muni.log4ts.util.ErrorHandler
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProjectsViewHolderAction @Inject constructor() {
    @Inject
    lateinit var projectRepository: FirebaseProjectRepository

    val TAG = ProjectsFragmentAction::class.simpleName

    suspend fun editProject(recyclerViewAdapter: ProjectsRecyclerViewAdapter, project: Project, view: View) {
        try {
            projectRepository.updateProjectInNamespace(project)
            recyclerViewAdapter.updateProject(project)
        } catch (e: Exception) {
            ErrorHandler.showErrorSnackbar(e, TAG, "Update of project failed...", view)
        }
    }

    suspend fun deleteProject(recyclerViewAdapter: ProjectsRecyclerViewAdapter, project: Project, view: View) {
        try {
            projectRepository.deleteProjectInNamespace(project)
            recyclerViewAdapter.deleteProject(project)
        } catch (e: Exception) {
            ErrorHandler.showErrorSnackbar(e, TAG, "Delete of project failed...", view)
        }
    }
}