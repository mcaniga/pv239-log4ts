package cz.muni.log4ts.extension

import com.google.firebase.Timestamp
import cz.muni.log4ts.data.entities.LogEntry
import cz.muni.log4ts.data.entities.NewLogEntry
import cz.muni.log4ts.data.ui.PickedDateTime
import java.time.LocalDateTime
import java.time.ZoneOffset

fun PickedDateTime.toTimestamp(): Timestamp =
    Timestamp(LocalDateTime.of(this.date, this.time).toEpochSecond(ZoneOffset.UTC), 0)