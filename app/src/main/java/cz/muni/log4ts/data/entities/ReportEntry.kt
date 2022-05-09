package cz.muni.log4ts.data.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ReportEntry(
    val username: String,
    val seconds: Long,
    val userId: String
): Parcelable
