package cz.muni.log4ts.repository

import android.util.Log
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.QuerySnapshot
import cz.muni.log4ts.dao.FirebaseProjectDao
import cz.muni.log4ts.data.entities.NewProject
import cz.muni.log4ts.data.entities.Project
import cz.muni.log4ts.mapper.ProjectMapper
import okhttp3.internal.format
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Project Repository for firebase cloud storage
 * Requires internet access
 */
@Singleton
class FirebaseProjectRepository @Inject constructor() : ProjectRepositoryInterface {
    @Inject
    lateinit var dao: FirebaseProjectDao

    @Inject
    lateinit var mapper: ProjectMapper

    private val TAG = FirebaseProjectRepository::class.simpleName;

    override suspend fun getAllProjectsInNamespace(namespaceId: String): List<Project> {
        Log.d(
            TAG,
            String.format("Fetching projects from Firebase with namespaceId %s", namespaceId)
        )
        val projectsDocuments: QuerySnapshot = dao.getAllProjectsDocumentsFromNamespace(namespaceId)
        Log.d(
            TAG,
            String.format(
                "All project documents for namespace are fetched, content of documents is: %s",
                projectsDocuments.documents
            )
        )
        val projects = mapper.makeProjectsFromProjectsDocuments(namespaceId, projectsDocuments)
        Log.d(TAG, String.format("Parsed projects are: %s", projects))
        return projects
    }

    override suspend fun addProjectInNamespace(newProject: NewProject): String {
        Log.d(
            TAG,
            String.format("Adding project %s to namespace %s", newProject, newProject.namespaceId)
        )
        val data: Map<String, Any> = mapper.makeFirebaseDataMapFromNewProject(newProject)
        Log.d(TAG, String.format("Made firebase data map: %s from given project", data))
        val projectDocument: DocumentReference =
            dao.addProjectDocument(newProject.namespaceId, data)
        Log.d(TAG, format("Successfully added the project, id: %s", projectDocument.id))
        return projectDocument.id
    }

    override suspend fun updateProjectInNamespace(project: Project) {
        val data: Map<String, Any> = mapper.makeFirebaseDataMapFromProject(project)
        Log.d(TAG, String.format("Made firebase data map: %s from given project", data))
        dao.updateProjectDocument(project.namespaceId, project.id, data)
        Log.d(TAG, "Successfully updated the project")
    }

    override suspend fun deleteProjectInNamespace(project: Project) {
        Log.d(
            TAG,
            String.format("Deleting log entry %s in namespace %s", project, project.namespaceId)
        )
        dao.deleteProjectDocument(project)
        Log.d(TAG, "Successfully deleted the project")
    }
}
