package cz.muni.log4ts.extension

import com.google.firebase.Timestamp
import kotlinx.datetime.Instant
import java.lang.Exception

/**
 * Makes new Firebase timestamp with subtracted amount of seconds
 */
fun Timestamp.subtractSeconds(seconds: Long): Timestamp {
    return Timestamp(this.seconds - seconds, this.nanoseconds)
}

fun Timestamp.toPrettyString(): String {
    val instantString = Instant.fromEpochSeconds(this.seconds, this.nanoseconds).toString()
    return tryToFormatInstantString(instantString)
}

private fun tryToFormatInstantString(instantString: String): String {
    return try {
        instantString.replace("T", " ").split(".")[0]
    } catch (e: Exception) {
        instantString
    }
}