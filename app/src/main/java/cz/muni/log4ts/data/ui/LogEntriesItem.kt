package cz.muni.log4ts.data.ui

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LogEntriesItem(
    val id: String,
    val name: String,
    val project: String,
    val loggedSeconds: Long,
): Parcelable