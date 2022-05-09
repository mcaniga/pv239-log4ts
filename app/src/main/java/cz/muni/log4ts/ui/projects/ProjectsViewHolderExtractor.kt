package cz.muni.log4ts.ui.projects

import cz.muni.log4ts.data.entities.Project
import cz.muni.log4ts.databinding.ItemProjectListBinding
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProjectsViewHolderExtractor @Inject constructor() {
    fun extractUpdatedProject(
        binding: ItemProjectListBinding,
        oldProject: Project,
    ): Project {
        val name = binding.nameEditView.text.toString()

        return Project(
            id = oldProject.id,
            namespaceId = oldProject.namespaceId,
            name = name,
            users = oldProject.users
        )
    }
}