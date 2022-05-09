package cz.muni.log4ts.data.dto


import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class ReportDetailDto(
    val userId: String,
    val username: String,
    val project: String,
    val minutes: Long,
    val lowerBound: Date,
    val upperBound: Date
): Parcelable
