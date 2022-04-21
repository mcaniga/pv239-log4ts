package cz.muni.log4ts

import android.app.Application
import cz.muni.log4ts.ui.auth.login.LoginFragment
import cz.muni.log4ts.ui.auth.register.RegisterFragment
import cz.muni.log4ts.ui.header.HeaderFragment
import cz.muni.log4ts.ui.logEntries.LogEntriesFragment
import cz.muni.log4ts.ui.logEntries.LogEntriesViewHolder
import cz.muni.log4ts.ui.logEntries.detail.LogEntriesDetailFragment
import cz.muni.log4ts.ui.logEntries.newLogEntry.NewLogEntryFragment
import cz.muni.log4ts.ui.projects.ProjectsFragment
import cz.muni.log4ts.ui.projects.ProjectsViewHolder
import cz.muni.log4ts.ui.projects.detail.ProjectDetailFragment
import dagger.Component
import javax.inject.Singleton

/** NOTE
 * If you get an error "DaggerApplicationComponent is not abstract..." ensure that injected classes
 * and their dependencies are not in package named 'new' or other reserved Java/Kotlin keyword
 * because it will break the reflection
**/
@Singleton
@Component
interface ApplicationComponent {
    fun injectLogEntriesFragmentDeps(logEntriesFragment: LogEntriesFragment)
    fun injectLoginFragmentDeps(loginFragment: LoginFragment)
    fun injectRegisterFragmentDeps(registerFragment: RegisterFragment)
    fun injectProjectsFragmentDeps(projectsFragment: ProjectsFragment)
    fun injectLogEntriesViewHolderDeps(logEntriesViewHolder: LogEntriesViewHolder)
    fun injectProjectsViewHolderDeps(projectsViewHolder: ProjectsViewHolder)
    fun injectHeaderFragmentDeps(headerFragment: HeaderFragment)
    fun injectLogEntriesDetailFragmentDeps(logEntriesDetailFragment: LogEntriesDetailFragment)
    fun injectProjectDetailFragmentDeps(projectDetailFragment: ProjectDetailFragment)
    fun injectNewLogEntryFragmentDeps(newLogEntryFragment: NewLogEntryFragment)
}

class Log4TSApplication: Application() {
    companion object {
        val appComponent: ApplicationComponent = DaggerApplicationComponent.create()
    }
}