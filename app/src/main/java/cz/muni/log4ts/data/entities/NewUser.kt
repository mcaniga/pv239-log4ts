package cz.muni.log4ts.data.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NewUser(
    val username: String,
    val email: String,
    val password: String,
): Parcelable