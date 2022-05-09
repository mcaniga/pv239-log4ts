package cz.muni.log4ts.mapper

import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import cz.muni.log4ts.data.entities.NewProject
import cz.muni.log4ts.data.entities.Project
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProjectMapper @Inject constructor() {
    fun makeProjectsFromProjectsDocuments(namespaceId: String, projectsDocuments: QuerySnapshot): List<Project> {
        return projectsDocuments.documents.map { makeProjectFromProjectDocument(namespaceId, it) }
    }

    private fun makeProjectFromProjectDocument(
        namespaceId: String,
        projectDocument: DocumentSnapshot
    ): Project {
        val data = projectDocument.data
        return Project(
            id = projectDocument.id,
            namespaceId = namespaceId,
            name = data?.get("name")!! as String,
            users = data["users"] as MutableList<String>
        )
    }

    fun makeFirebaseDataMapFromNewProject(newProject: NewProject): Map<String, Any> =
        hashMapOf(
            "name" to newProject.name
        )

    fun makeFirebaseDataMapFromProject(project: Project): Map<String, Any> =
        hashMapOf(
            "name" to project.name,
            "users" to project.users
        )
}