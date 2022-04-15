package cz.muni.log4ts.data.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserData(
    val userId: String,
    val logEntries: List<LogEntry>,
): Parcelable