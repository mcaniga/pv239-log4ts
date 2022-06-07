package cz.muni.log4ts.ui.logEntries.detail

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.util.Log
import android.view.View
import android.widget.TextView
import cz.muni.log4ts.data.entities.LogEntry
import cz.muni.log4ts.data.ui.PickedDateTime
import cz.muni.log4ts.extension.toPickedDateTime
import cz.muni.log4ts.extension.toPrettyString
import cz.muni.log4ts.extension.toTimestamp
import cz.muni.log4ts.repository.FirebaseLogRepository
import cz.muni.log4ts.util.ErrorHandler
import java.time.LocalDate
import java.time.LocalTime
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LogEntriesDateTimePickerHandler @Inject constructor() {
    val TAG = LogEntriesDateTimePickerHandler::class.simpleName

    fun setLogTime(
        context: Context,
        view: View,
        pickedTimeToSet: PickedDateTime,
        minDate: PickedDateTime?,
        maxDate: PickedDateTime?,
        textView: TextView
    ) {
        val timePicker = TimePickerDialog(context, { _, hour, minute ->
            run {
                val time = LocalTime.of(hour, minute)
                Log.d("LogEntriesDateTimePickerHandler", time.toString())
                if (isPickedDateInvalid(minDate, maxDate, pickedTimeToSet)) {
                    ErrorHandler.showErrorSnackbar("Picked time is not valid", view)
                } else {
                    pickedTimeToSet.time = time
                    textView.text = pickedTimeToSet.toTimestamp().toPrettyString()
                }
            }
        }, 0, 0, true)

        val datePicker = DatePickerDialog(context)
        datePicker.setOnDateSetListener { _, year, month, day ->
            run {
                val date = LocalDate.of(year, month + 1, day)
                pickedTimeToSet.date = date
                Log.d("LogEntriesDateTimePickerHandler", date.toString())
                timePicker.show()
            }
        }
        if (minDate != null) {
            datePicker.datePicker.minDate = minDate.date.toEpochDay()
        }
        datePicker.datePicker.maxDate = Calendar.getInstance().timeInMillis
        datePicker.show()
    }

    private fun isPickedDateInvalid(
        minDate: PickedDateTime?,
        maxDate: PickedDateTime?,
        pickedTimeToSet: PickedDateTime
    ) =
        (minDate != null && (pickedTimeToSet.date < minDate.date || pickedTimeToSet.time < minDate.time)) ||
                (maxDate != null && (pickedTimeToSet.date > maxDate.date || pickedTimeToSet.time > maxDate.time))
}