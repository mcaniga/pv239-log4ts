package cz.muni.log4ts

import android.app.Application
import cz.muni.log4ts.ui.auth.login.LoginFragment
import cz.muni.log4ts.ui.auth.register.RegisterFragment
import cz.muni.log4ts.ui.logEntries.LogEntriesFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component
interface ApplicationComponent {
    fun injectLogEntriesFragmentDeps(logEntriesFragment: LogEntriesFragment)
    fun injectLoginFragmentDeps(loginFragment: LoginFragment)
    fun injectRegisterFragmentDeps(registerFragment: RegisterFragment)
}

class Log4TSApplication: Application() {
    companion object {
        val appComponent = DaggerApplicationComponent.create()
    }
}