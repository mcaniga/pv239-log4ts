package cz.muni.log4ts.extension

import cz.muni.log4ts.data.ui.PickedDateTime
import java.time.LocalDateTime

fun LocalDateTime.toPickedDateTime(): PickedDateTime =
    PickedDateTime(this.toLocalDate(), this.toLocalTime())