package cz.muni.log4ts.mapper

import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import cz.muni.log4ts.data.entities.Namespace
import cz.muni.log4ts.data.entities.NewNamespace
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NamespaceMapper @Inject constructor() {
    fun makeNamespacesFromNamespaceDocuments(namespaceDocuments: QuerySnapshot): List<Namespace> {
        return namespaceDocuments.documents.map { makeNamespaceFromNamespaceDocument(it) }
    }

    fun makeNamespaceFromNamespaceDocument(
        namespaceDocument: DocumentSnapshot
    ): Namespace {
        val data = namespaceDocument.data
        return Namespace(
            id = namespaceDocument.id,
            name = data?.get("name")!! as String,
            ownerId = data?.get("ownerId")!! as String
        )
    }

    fun makeFirebaseDataMapFromNewNamespace(newNamespace: NewNamespace): Map<String, Any> =
        hashMapOf(
            "name" to newNamespace.name, "ownerId" to newNamespace.ownerId
        )

    fun makeFirebaseDataMapFromNamespace(namespace: Namespace): Map<String, Any> =
        hashMapOf(
            "name" to namespace.name, "ownerId" to namespace.ownerId
        )
}