package cz.muni.log4ts.util

import android.content.Context
import android.widget.Toast
import com.google.firebase.Timestamp
import java.text.SimpleDateFormat
import java.util.*

fun Context.toast(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}

fun Long.getNowFormattedDateString(): String {
    val df = SimpleDateFormat("dd.mm.YYYY")
    val date = Date(this)
    return df.format(date)
}

/**
 * Makes new Firebase timestamp with subtracted amount of seconds
 */
fun Timestamp.subtractSeconds(seconds: Long): Timestamp {
    return Timestamp(this.seconds - seconds, this.nanoseconds)
}