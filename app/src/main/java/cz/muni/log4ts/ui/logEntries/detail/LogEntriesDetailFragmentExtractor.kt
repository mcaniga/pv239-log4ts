package cz.muni.log4ts.ui.logEntries.detail

import cz.muni.log4ts.data.entities.LogEntry
import cz.muni.log4ts.data.ui.PickedDateTime
import cz.muni.log4ts.databinding.FragmentLogEditBinding
import cz.muni.log4ts.extension.toTimestamp
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LogEntriesDetailFragmentExtractor @Inject constructor() {
    fun extractUpdatedLogEntry(
        binding: FragmentLogEditBinding,
        oldLogEntry: LogEntry,
        pickedStartTime: PickedDateTime,
        pickedEndTime: PickedDateTime
    ): LogEntry {
        val name = binding.nameInput.text.toString()
        val project = binding.projectsSpinner.selectedItem.toString()

        val startTime = pickedStartTime.toTimestamp()
        val endTime = pickedEndTime.toTimestamp()
        val loggedSeconds = endTime.seconds - startTime.seconds

        return LogEntry(
            id = oldLogEntry.id,
            userId = oldLogEntry.userId,
            name = name,
            namespace = oldLogEntry.namespace,
            project = project,
            startTime = startTime,
            endTime = endTime,
            loggedSeconds = loggedSeconds,
        )
    }
}