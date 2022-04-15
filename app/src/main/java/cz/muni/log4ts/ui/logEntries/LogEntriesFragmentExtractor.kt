package cz.muni.log4ts.ui.logEntries

import com.google.firebase.Timestamp
import cz.muni.log4ts.data.entities.NewLogEntry
import cz.muni.log4ts.databinding.FragmentLogEntriesBinding
import cz.muni.log4ts.extension.subtractSeconds

class LogEntriesFragmentExtractor {
    fun extractNewLogEntry(
        binding: FragmentLogEntriesBinding,
        userId: String,
        namespace: String,
        project: String
    ): NewLogEntry {
        val name = binding.logTextInput.text.toString()
        val loggedSeconds = binding.logDurationTextView // TODO: parse and connect use in NewLogEntry
        val endTime = Timestamp.now()
        val startTime = endTime.subtractSeconds(90) // TODO: subtract parsed loggedSeconds

        return NewLogEntry(
            userId = userId,
            name = name,
            namespace = namespace,
            project = project,
            startTime = startTime,
            endTime = endTime,
            loggedSeconds = 90
        )
    }
}