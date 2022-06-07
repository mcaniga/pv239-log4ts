package cz.muni.log4ts.ui.projects.detail

import android.R
import android.content.Context
import android.widget.ArrayAdapter
import cz.muni.log4ts.repository.FirebaseProjectRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProjectSpinnerAdapterFactory  @Inject constructor() {
    suspend fun makeProjectSpinnerAdapter(
        context: Context,
        projectRepository: FirebaseProjectRepository,
    ): ArrayAdapter<String>{
        val projects =  projectRepository.getAllProjectsInNamespace("global")
        val projectNames: List<String> = projects.map { it.name }

        return ArrayAdapter<String>(
            context,
            R.layout.simple_spinner_item,
            projectNames
        ).also {
            it.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        }
    }
}