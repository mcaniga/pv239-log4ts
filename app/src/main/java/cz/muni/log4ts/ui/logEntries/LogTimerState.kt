package cz.muni.log4ts.ui.logEntries

import android.content.Intent
import kotlin.math.roundToInt

class LogTimerState {
    companion object {
        const val INITIAL_TIME = 0.0
    }

    var timerStarted = false
    lateinit var serviceIntent: Intent
    var time = INITIAL_TIME

    fun startTimer() {
        timerStarted = true;
    }

    fun stopTimer() {
        timerStarted = false
    }

    fun refreshTime() {
        time = INITIAL_TIME
    }

    fun getTimeString(): String {
        val resultInt = time.roundToInt()
        val hours = resultInt % 86400 / 3600
        val minutes = resultInt % 86400 % 3600 / 60
        val seconds = resultInt % 86400 % 3600 % 60

        return makeTimeString(hours, minutes, seconds)
    }

    private fun makeTimeString(hour: Int, min: Int, sec: Int): String =
        String.format("%02d:%02d:%02d", hour, min, sec)
}