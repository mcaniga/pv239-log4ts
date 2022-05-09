package cz.muni.log4ts.dao

import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import cz.muni.log4ts.data.entities.Namespace
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirebaseNamespaceDao @Inject constructor() {
    private val db = Firebase.firestore

    suspend fun getAllNamespaceDocuments(): QuerySnapshot {
        return db
            .collection("namespaces")
            .get()
            .await()
    }

    suspend fun addNamespaceDocument(
        data: Map<String, Any>
    ): DocumentReference {
        return db
            .collection("namespaces")
            .add(data)
            .await()
    }

    suspend fun updateNamespaceDocument(id: String, data: Map<String, Any>) {
        db
            .collection("namespaces").document(id)
            .set(data)
            .await()
    }

    suspend fun deleteNamespaceDocument(namespace: Namespace) {
        db
            .collection("namespaces").document(namespace.id)
            .delete()
            .await()
    }
}