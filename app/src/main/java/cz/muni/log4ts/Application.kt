package cz.muni.log4ts

import android.app.Application
import cz.muni.log4ts.ui.logEntries.LogEntriesFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component
interface ApplicationComponent {
    fun injectLogEntriesFragmentDeps(logEntriesFragment: LogEntriesFragment)
}

class Log4TSApplication: Application() {
    companion object {
        val appComponent = DaggerApplicationComponent.create()
    }
}