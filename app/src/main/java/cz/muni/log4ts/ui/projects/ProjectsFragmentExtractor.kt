package cz.muni.log4ts.ui.projects

import cz.muni.log4ts.data.entities.NewProject
import cz.muni.log4ts.databinding.FragmentProjectsBinding
import cz.muni.log4ts.databinding.ItemProjectListBinding
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProjectsFragmentExtractor @Inject constructor() {
    fun extractNewProject(
        binding: FragmentProjectsBinding,
        namespaceId: String,
    ): NewProject {
        val name = binding.newProjectInput.text.toString()

        return NewProject(
            namespaceId = namespaceId,
            name = name
        )
    }
}