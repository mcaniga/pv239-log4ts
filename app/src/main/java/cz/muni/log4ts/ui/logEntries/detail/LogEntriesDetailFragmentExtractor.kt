package cz.muni.log4ts.ui.logEntries.detail

import cz.muni.log4ts.data.entities.LogEntry
import cz.muni.log4ts.databinding.FragmentLogEditBinding
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LogEntriesDetailFragmentExtractor @Inject constructor() {
    fun extractUpdatedLogEntry(binding: FragmentLogEditBinding, oldLogEntry: LogEntry): LogEntry {
        val name = binding.nameInput.text.toString()
        val project = binding.projectsSpinner.selectedItem.toString()

        return LogEntry(
            id = oldLogEntry.id,
            userId = oldLogEntry.userId,
            name = name,
            namespace = oldLogEntry.namespace,
            project = project,
            startTime = oldLogEntry.startTime, // TODO: get from binding
            endTime = oldLogEntry.endTime, // TODO: get from binding
            loggedSeconds = oldLogEntry.loggedSeconds, // TODO: get from binding
        )
    }
}