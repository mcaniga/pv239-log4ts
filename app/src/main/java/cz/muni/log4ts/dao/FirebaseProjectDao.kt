package cz.muni.log4ts.dao

import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import cz.muni.log4ts.data.entities.Project
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirebaseProjectDao @Inject constructor() {
    private val db = Firebase.firestore

    suspend fun getAllProjectsDocumentsFromNamespace(namespaceId: String): QuerySnapshot {
        return db
            .collection("namespaces").document(namespaceId)
            .collection("projects")
            .get()
            .await()
    }

    suspend fun addProjectDocument(
        namespaceId: String,
        data: Map<String, Any>
    ): DocumentReference {
        return db
            .collection("namespaces").document(namespaceId)
            .collection("projects")
            .add(data)
            .await()
    }

    suspend fun updateProjectDocument(namespaceId: String, id: String, data: Map<String, Any>) {
        db
            .collection("namespaces").document(namespaceId)
            .collection("projects").document(id)
            .set(data)
            .await()
    }

    suspend fun deleteProjectDocument(project: Project) {
        db
            .collection("namespaces").document(project.namespaceId)
            .collection("projects").document(project.id)
            .delete()
            .await()
    }
}