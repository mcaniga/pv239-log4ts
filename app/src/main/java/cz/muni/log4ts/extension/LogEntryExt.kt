package cz.muni.log4ts.extension

import cz.muni.log4ts.data.entities.LogEntry
import cz.muni.log4ts.data.ui.LogEntriesItem

fun LogEntry.toLogEntriesItem() =
    LogEntriesItem(
        id = this.id,
        name = this.name,
        project = this.project,
        loggedSeconds = this.loggedSeconds
    )