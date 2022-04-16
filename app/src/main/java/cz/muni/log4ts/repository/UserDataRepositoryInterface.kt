package cz.muni.log4ts.repository

interface UserDataRepositoryInterface {
    suspend fun getCurrentUsername(): String
}