package cz.muni.log4ts.data.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NewLogArg(
    val elapsedSeconds: Long,
): Parcelable