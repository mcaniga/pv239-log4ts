package cz.muni.log4ts.data.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserData(
    val userId: String,
    val username: String,
    val useremail: String,
    val projects: MutableList<String>
): Parcelable