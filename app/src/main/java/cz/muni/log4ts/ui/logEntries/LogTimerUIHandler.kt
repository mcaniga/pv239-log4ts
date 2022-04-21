package cz.muni.log4ts.ui.logEntries

import androidx.fragment.app.FragmentActivity
import cz.muni.log4ts.databinding.FragmentLogEntriesBinding
import cz.muni.log4ts.service.TimerService
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.math.roundToLong

@Singleton
class LogTimerUIHandler @Inject constructor() {
    fun startTimer(
        logTimerState: LogTimerState,
        binding: FragmentLogEntriesBinding,
        activity: FragmentActivity
    ) {
        logTimerState.serviceIntent.putExtra(TimerService.TIME_EXTRA, logTimerState.time)
        activity.startService(logTimerState.serviceIntent)
        binding.logButton.text = STOP_LOG_BUTTON_TEXT
        logTimerState.startTimer()
    }

    fun stopTimer(
        logTimerState: LogTimerState,
        binding: FragmentLogEntriesBinding,
        activity: FragmentActivity
    ): Long {
        val elapsedSeconds = logTimerState.time.roundToLong()
        refreshTimerState(logTimerState)
        refreshTimerUI(binding)
        activity.stopService(logTimerState.serviceIntent)
        return elapsedSeconds
    }

    private fun refreshTimerState(logTimerState: LogTimerState) {
        logTimerState.stopTimer()
        logTimerState.refreshTime()
    }

    private fun refreshTimerUI(binding: FragmentLogEntriesBinding) {
        binding.logDurationTextView.text = INITIAL_TIMER_TEXT
        binding.logButton.text = INITIAL_LOG_BUTTON_TEXT
    }

    companion object {
        const val INITIAL_TIMER_TEXT = "00:00:00"
        const val INITIAL_LOG_BUTTON_TEXT = "Start"
        const val STOP_LOG_BUTTON_TEXT = "Stop"
    }
}