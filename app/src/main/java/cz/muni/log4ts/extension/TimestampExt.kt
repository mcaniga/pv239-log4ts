package cz.muni.log4ts.extension

import com.google.firebase.Timestamp

/**
 * Makes new Firebase timestamp with subtracted amount of seconds
 */
fun Timestamp.subtractSeconds(seconds: Long): Timestamp {
    return Timestamp(this.seconds - seconds, this.nanoseconds)
}