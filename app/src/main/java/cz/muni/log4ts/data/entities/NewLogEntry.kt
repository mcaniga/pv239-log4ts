package cz.muni.log4ts.data.entities

import android.os.Parcelable
import com.google.firebase.Timestamp
import kotlinx.parcelize.Parcelize

@Parcelize
data class NewLogEntry(
    val userId: String,
    val name: String,
    val namespace: String,
    val project: String,
    val startTime: Timestamp,
    val endTime: Timestamp,
    val loggedSeconds: Long,
): Parcelable