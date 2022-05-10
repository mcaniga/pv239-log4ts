package cz.muni.log4ts.ui.projects.detail

import android.util.Log
import android.view.View
import androidx.navigation.NavController
import cz.muni.log4ts.data.entities.Project
import cz.muni.log4ts.data.entities.UserData
import cz.muni.log4ts.exceptions.UserAlreadyPresent
import cz.muni.log4ts.exceptions.UserNotFound
import cz.muni.log4ts.exceptions.UserNotPresent
import cz.muni.log4ts.repository.FirebaseProjectRepository
import cz.muni.log4ts.repository.FirebaseUserDataRepository
import cz.muni.log4ts.util.ErrorHandler
import cz.muni.log4ts.util.ErrorHandler.StaticMethods.showActionWasSucessfullSnackbar
import java.lang.String.format
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProjectDetailFragmentAction @Inject constructor() {
    @Inject
    lateinit var projectRepository: FirebaseProjectRepository

    @Inject
    lateinit var userRepository: FirebaseUserDataRepository

    val TAG = ProjectDetailFragmentAction::class.simpleName

    suspend fun editProject(navController: NavController, project: Project, view: View) {
        try {
            Log.d(TAG, format("Project to update: %s", project))
            projectRepository.updateProjectInNamespace(project)
            showActionWasSucessfullSnackbar(view)
            navController.navigate(ProjectDetailFragmentDirections.actionProjectDetailFragmentToProjectsFragment())
        } catch (e: Exception) {
            ErrorHandler.showErrorSnackbar(e, TAG, "Editing of project failed...", view)
        }
    }

    suspend fun editProjectAddUser(project: Project, useremail: String, view: View) {
        try {
            Log.d(TAG, "Entered user email: $useremail")
            val user = getUserByEmail(useremail)
            val updatedProject = makeUpdatedProjectAdd(project, user.userId)
            Log.d(TAG, "Updated the project with new user $updatedProject")

            projectRepository.updateProjectInNamespace(updatedProject)
            val updatedUser = makeUpdatedUserAdd(user, project.id)
            Log.d(TAG, "Updated the user with new project id")
            userRepository.updateUserDataDocument(updatedUser)
            showActionWasSucessfullSnackbar(view)
        } catch (e: UserNotFound) {
            ErrorHandler.showErrorSnackbar(e, TAG, "No user with such email", view)
        }  catch (e: UserAlreadyPresent) {
            ErrorHandler.showErrorSnackbar(e, TAG, "User with email: $useremail is already present in the project", view)
        } catch (e: java.lang.Exception) {
            ErrorHandler.showErrorSnackbar(e, TAG, "Adding user to the project failed...", view)
        }
    }


    suspend fun editProjectRemoveUser(project: Project, useremail: String, view: View) {
        try {
            val user = getUserByEmail(useremail)
            Log.d(TAG, "User id to be removed: ${user.userId}")
            val updatedProject = makeUpdatedProjectRemove(project, user.userId)
            projectRepository.updateProjectInNamespace(updatedProject)
            val updatedUser = makeUpdatedUserRemove(user, project.id)
            userRepository.updateUserDataDocument(updatedUser)
            showActionWasSucessfullSnackbar(view)
        } catch (e: UserNotFound) {
            ErrorHandler.showErrorSnackbar(e, TAG, "No user with such email", view)
        } catch (e: UserNotPresent) {
            ErrorHandler.showErrorSnackbar(e, TAG, "User with email: $useremail is not present in the project", view)
        } catch (e: Exception) {
            ErrorHandler.showErrorSnackbar(e, TAG, "Removing user from the project failed...", view)
        }
    }

    private suspend fun getUserByEmail(email: String): UserData {
        return userRepository.getUserDataDocumentByEmail(email)
    }

    private fun makeUpdatedProjectRemove(oldProject: Project, userId: String): Project {
        val newUsers = oldProject.users.toMutableList()
        newUsers.remove(userId)
        val updatedProject = Project(
            id = oldProject.id,
            namespaceId = oldProject.namespaceId,
            name = oldProject.name,
            users = newUsers
        )
        if (updatedProject.users.size == oldProject.users.size) {
            throw UserNotPresent("User not present")
        }
        return updatedProject
    }

    private fun makeUpdatedProjectAdd(oldProject: Project, userId: String): Project {
        if (oldProject.users.contains(userId)) {
            throw UserAlreadyPresent("User already present")
        }
        oldProject.users.add(userId)
        return Project(
            id = oldProject.id,
            namespaceId = oldProject.namespaceId,
            name = oldProject.name,
            users = oldProject.users
        )
    }

    private fun makeUpdatedUserRemove(oldUser: UserData, projectId: String): UserData {
        return UserData(
            userId = oldUser.userId,
            username = oldUser.username,
            useremail = oldUser.useremail,
            projects = oldUser.projects.filter { it != projectId } as MutableList<String>
        )
    }

    private fun makeUpdatedUserAdd(oldUser: UserData, projectId: String): UserData {
        oldUser.projects.add(projectId)
        return UserData(
            userId = oldUser.userId,
            username = oldUser.username,
            useremail = oldUser.useremail,
            projects = oldUser.projects
        )
    }
}