package cz.muni.log4ts.repository

import cz.muni.log4ts.data.entities.Namespace
import cz.muni.log4ts.data.entities.NewNamespace

interface NamespaceRepositoryInterface {
    suspend fun getAllNamespaces(): List<Namespace>
    suspend fun addNamespace(newNamespace: NewNamespace): String
    suspend fun updateNamespace(namespace: Namespace)
    suspend fun deleteNamespace(namespace: Namespace)
}