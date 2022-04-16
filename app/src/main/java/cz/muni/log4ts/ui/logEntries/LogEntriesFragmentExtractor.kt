package cz.muni.log4ts.ui.logEntries

import com.google.firebase.Timestamp
import cz.muni.log4ts.data.entities.NewLogEntry
import cz.muni.log4ts.databinding.FragmentLogEntriesBinding
import cz.muni.log4ts.extension.subtractSeconds
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LogEntriesFragmentExtractor @Inject constructor() {
    fun extractNewLogEntry(
        binding: FragmentLogEntriesBinding,
        userId: String,
        namespace: String,
        project: String,
        loggedSeconds: Long
    ): NewLogEntry {
        val name = binding.logTextInput.text.toString()
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