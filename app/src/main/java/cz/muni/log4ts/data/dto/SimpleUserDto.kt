package cz.muni.log4ts.data.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SimpleUserDto(
    val userId: String,
    val username: String
): Parcelable
