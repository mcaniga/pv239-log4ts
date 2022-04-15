package cz.muni.log4ts.extension

import java.text.SimpleDateFormat
import java.util.*

fun Long.getNowFormattedDateString(): String {
    val df = SimpleDateFormat("dd.mm.YYYY")
    val date = Date(this)
    return df.format(date)
}