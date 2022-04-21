package cz.muni.log4ts.ui.projects.detail

import cz.muni.log4ts.data.entities.Project
import cz.muni.log4ts.databinding.FragmentProjectEditBinding
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProjectDetailFragmentExtractor @Inject constructor() {
    fun extractUpdatedProject(binding: FragmentProjectEditBinding, oldProject: Project): Project {
        val name = binding.nameInput.text.toString()

        return Project(
            id = oldProject.id,
            namespaceId = oldProject.namespaceId,
            name = name,
        )
    }
}