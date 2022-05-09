package cz.muni.log4ts.repository

import cz.muni.log4ts.data.entities.UserData

interface UserDataRepositoryInterface {
    suspend fun getCurrentUsername(): String
    suspend fun updateUserDataDocument(userData: UserData)
}