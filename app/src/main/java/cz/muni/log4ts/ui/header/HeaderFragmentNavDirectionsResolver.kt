package cz.muni.log4ts.ui.header

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import cz.muni.log4ts.ui.logEntries.LogEntriesFragment
import cz.muni.log4ts.ui.logEntries.LogEntriesFragmentDirections
import cz.muni.log4ts.ui.projects.ProjectsFragment
import cz.muni.log4ts.ui.projects.ProjectsFragmentDirections
import cz.muni.log4ts.ui.reports.ReportFragment
import cz.muni.log4ts.ui.reports.ReportFragmentDirections
import java.lang.String.format
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HeaderFragmentNavDirectionsResolver @Inject constructor() {
    val TAG = HeaderFragmentNavDirectionsResolver::class.simpleName

    fun getSignOutDirections(headerFragment: Fragment): NavDirections {
        val parentFragmentName =  getParentFragmentName(headerFragment)
        Log.d(TAG, format("Parent fragment name is: %s", parentFragmentName))
        return getSignOutDirectionsByParentFragmentName(parentFragmentName)
    }

    // TODO: add all fragments that are using HeaderFragment
    private fun getSignOutDirectionsByParentFragmentName(parentFragmentName: String): NavDirections {
        return when (parentFragmentName) {
            ProjectsFragment::class.simpleName -> {
                ProjectsFragmentDirections.actionProjectsFragmentToLoginFragment()
            }
            LogEntriesFragment::class.simpleName -> {
                LogEntriesFragmentDirections.actionLogEntriesFragmentToLoginFragment()
            }
            ReportFragment::class.simpleName -> {
                ReportFragmentDirections.actionReportsFragmentToLoginFragment()
            }
            else -> {
                throw IllegalStateException("Unknown parent fragment in HeaderFragmentNavDirectionsResolver::getSignOutDirections")
            }
        }
    }

    private fun getParentFragmentName(headerFragment: Fragment): String =
        headerFragment.requireParentFragment()::class.simpleName!!
}