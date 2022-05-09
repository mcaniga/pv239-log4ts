package cz.muni.log4ts.data.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Project(
    val id: String,
    val namespaceId: String,
    val name: String,
    val users: MutableList<String>
): Parcelable