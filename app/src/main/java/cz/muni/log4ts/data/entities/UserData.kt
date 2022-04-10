package cz.muni.log4ts.data.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserData(
    val logEntries: List<LogEntry>,
    val userId: Int,
): Parcelable