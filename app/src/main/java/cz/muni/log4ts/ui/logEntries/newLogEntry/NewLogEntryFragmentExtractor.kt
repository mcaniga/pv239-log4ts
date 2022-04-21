package cz.muni.log4ts.ui.logEntries.newLogEntry

import com.google.firebase.Timestamp
import cz.muni.log4ts.data.entities.NewLogEntry
import cz.muni.log4ts.databinding.FragmentLogNewBinding
import cz.muni.log4ts.extension.subtractSeconds
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewLogEntryFragmentExtractor @Inject constructor() {
    fun extractNewLogEntry(
        binding: FragmentLogNewBinding,
        userId: String,
        namespace: String,
        loggedSeconds: Long
    ): NewLogEntry {
        val name = binding.nameInput.text.toString()
        val project = binding.projectsSpinner.selectedItem.toString()
        val endTime = Timestamp.now()
        val startTime = endTime.subtractSeconds(loggedSeconds)

        return NewLogEntry(
            userId = userId,
            name = name,
            namespace = namespace,
            project = project,
            startTime = startTime,
            endTime = endTime,
            loggedSeconds = loggedSeconds
        )
    }
}