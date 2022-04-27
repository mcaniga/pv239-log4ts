package cz.muni.log4ts.extension

import com.google.firebase.Timestamp
import kotlinx.datetime.Instant

/**
 * Makes new Firebase timestamp with subtracted amount of seconds
 */
fun Timestamp.subtractSeconds(seconds: Long): Timestamp {
    return Timestamp(this.seconds - seconds, this.nanoseconds)
}

fun Timestamp.toInstantString() =
    Instant.fromEpochSeconds(this.seconds, this.nanoseconds).toString()