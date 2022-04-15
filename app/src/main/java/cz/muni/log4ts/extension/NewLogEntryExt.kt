package cz.muni.log4ts.extension

import cz.muni.log4ts.data.entities.LogEntry
import cz.muni.log4ts.data.entities.NewLogEntry

fun NewLogEntry.toLogEntry(id: String): LogEntry =
    LogEntry(
        id = id,
        userId = this.userId,
        name = this.name,
        startTime = this.startTime,
        endTime = this.endTime,
        loggedSeconds = this.loggedSeconds,
        namespace = this.namespace,
        project = this.project
    )