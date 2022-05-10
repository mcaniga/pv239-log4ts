package cz.muni.log4ts.repository

import android.util.Log
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.QuerySnapshot
import cz.muni.log4ts.dao.FirebaseNamespaceDao
import cz.muni.log4ts.data.entities.Namespace
import cz.muni.log4ts.data.entities.NewNamespace
import cz.muni.log4ts.mapper.NamespaceMapper
import java.lang.String.format
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Project Repository for firebase cloud storage
 * Requires internet access
 */
@Singleton
class FirebaseNamespaceRepository @Inject constructor() : NamespaceRepositoryInterface {
    @Inject
    lateinit var dao: FirebaseNamespaceDao

    @Inject
    lateinit var mapper: NamespaceMapper

    private val TAG = FirebaseNamespaceRepository::class.simpleName;

    override suspend fun getAllNamespaces(): List<Namespace> {
        Log.d(
            TAG,
            String.format("Fetching namespaces from Firebase")
        )
        val namespaceDocuments: QuerySnapshot = dao.getAllNamespaceDocuments()
        Log.d(
            TAG,
            String.format(
                "All namespace documents are fetched, content of documents is: %s",
                namespaceDocuments.documents
            )
        )
        val projects = mapper.makeNamespacesFromNamespaceDocuments(namespaceDocuments)
        Log.d(TAG, String.format("Parsed namespaces are: %s", projects))
        return projects
    }

    override suspend fun addNamespace(newNamespace: NewNamespace): String {
        Log.d(
            TAG,
            String.format("Adding namespace %s", newNamespace)
        )
        val data: Map<String, Any> = mapper.makeFirebaseDataMapFromNewNamespace(newNamespace)
        Log.d(TAG, String.format("Made firebase data map: %s from given namespace", data))
        val namespaceDocument: DocumentReference =
            dao.addNamespaceDocument(data)
        Log.d(TAG, format("Successfully added the namespace, id: %s", namespaceDocument.id))
        return namespaceDocument.id
    }

    override suspend fun updateNamespace(namespace: Namespace) {
        val data: Map<String, Any> = mapper.makeFirebaseDataMapFromNamespace(namespace)
        Log.d(TAG, String.format("Made firebase data map: %s from given namespace", data))
        dao.updateNamespaceDocument(namespace.id, data)
        Log.d(TAG, "Successfully updated the namespace")
    }

    override suspend fun deleteNamespace(namespace: Namespace) {
        Log.d(
            TAG,
            String.format("Deleting namespace %s", namespace)
        )
        dao.deleteNamespaceDocument(namespace)
        Log.d(TAG, "Successfully deleted the namespace")
    }
}