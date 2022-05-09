package cz.muni.log4ts.data.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NewNamespace(
    val ownerId: String,
    val name: String,
): Parcelable
