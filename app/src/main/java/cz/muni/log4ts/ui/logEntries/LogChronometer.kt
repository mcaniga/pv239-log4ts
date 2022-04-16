package cz.muni.log4ts.ui.logEntries

import android.os.SystemClock
import android.widget.Chronometer
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LogChronometer  @Inject constructor() {
    fun start(chronometer: Chronometer) {
        chronometer.base = SystemClock.elapsedRealtime()
        chronometer.start()
    }

    fun stop(chronometer: Chronometer): Long {
        val elapsedSeconds = getElapsedTimeInSeconds(chronometer)
        chronometer.stop()
        chronometer.base = SystemClock.elapsedRealtime()
        return elapsedSeconds
    }

    fun getElapsedTimeInSeconds(chronometer: Chronometer): Long {
        return getElapsedTimeInMillis(chronometer) / 1000
    }

    private fun getElapsedTimeInMillis(chronometer: Chronometer): Long {
        return SystemClock.elapsedRealtime() - chronometer.base
    }
}