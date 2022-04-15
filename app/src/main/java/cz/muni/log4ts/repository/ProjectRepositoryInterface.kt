package cz.muni.log4ts.repository

import cz.muni.log4ts.data.entities.NewProject
import cz.muni.log4ts.data.entities.Project

interface ProjectRepositoryInterface {
    suspend fun getAllProjectsInNamespace(namespaceId: String): List<Project>
    suspend fun addProjectInNamespace(newProject: NewProject): String
    suspend fun updateProjectInNamespace(project: Project)
    suspend fun deleteProjectInNamespace(project: Project)
}