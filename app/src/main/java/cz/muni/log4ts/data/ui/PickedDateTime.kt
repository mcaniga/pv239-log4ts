package cz.muni.log4ts.data.ui

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDate
import java.time.LocalTime

@Parcelize
data class PickedDateTime(
    var date: LocalDate,
    var time: LocalTime,
): Parcelable