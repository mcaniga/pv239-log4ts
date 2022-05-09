package cz.muni.log4ts.extension

import com.google.firebase.Timestamp
import cz.muni.log4ts.data.ui.PickedDateTime
import kotlinx.datetime.Instant
import java.lang.Exception
import java.time.LocalDateTime
import java.time.ZoneOffset

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

fun Timestamp.toPickedDateTime(): PickedDateTime =
    LocalDateTime.ofEpochSecond(this.seconds, this.nanoseconds, ZoneOffset.UTC).toPickedDateTime()

private fun tryToFormatInstantString(instantString: String): String {
    return try {
        instantString
            .replace("T", " ")
            .split(".")[0]
            .replace("Z", "")
    } catch (e: Exception) {
        instantString
    }
}