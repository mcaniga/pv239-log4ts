package cz.muni.log4ts.ui.projects.detail

import android.view.View
import cz.muni.log4ts.data.entities.Project
import cz.muni.log4ts.repository.FirebaseProjectRepository
import cz.muni.log4ts.util.ErrorHandler
import cz.muni.log4ts.util.ErrorHandler.StaticMethods.showActionWasSucessfullSnackbar
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProjectDetailFragmentAction @Inject constructor() {
    @Inject
    lateinit var projectRepository: FirebaseProjectRepository

    val TAG = ProjectDetailFragmentAction::class.simpleName

    suspend fun editProject(project: Project, view: View) {
        try {
            projectRepository.updateProjectInNamespace(project)
            showActionWasSucessfullSnackbar(view)
        } catch (e: Exception) {
            ErrorHandler.showErrorSnackbar(e, TAG, "Editing of project failed...", view)
        }
    }
}