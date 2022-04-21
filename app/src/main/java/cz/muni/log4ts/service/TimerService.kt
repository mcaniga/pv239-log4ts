package cz.muni.log4ts.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import java.lang.String.format
import java.util.*

class TimerService : Service()
{
    override fun onBind(p0: Intent?): IBinder? = null

    private val secondsTimer = Timer()

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int
    {
        Log.d(TAG, "TimerService is starting...")
        val seconds = extractSecondsFromIntent(intent)
        Log.d(TAG, format("Time from intent is %s", seconds))
        runTimeTaskEachSecond(seconds)
        Log.d(TAG, "Scheduled timer at fixed rate")
        return START_NOT_STICKY
    }

    override fun onDestroy()
    {
        secondsTimer.cancel()
        super.onDestroy()
    }

    private fun extractSecondsFromIntent(intent: Intent): Double {
        return intent.getDoubleExtra(TIME_EXTRA, 0.0)
    }

    private fun runTimeTaskEachSecond(seconds: Double) {
        secondsTimer.scheduleAtFixedRate(TimeTask(seconds), 0, ONE_SECOND)
    }

    private inner class TimeTask(private var seconds: Double) : TimerTask()
    {
        override fun run()
        {
            broadcastTimerUpdateAndIncreaseSeconds()
        }

        private fun broadcastTimerUpdateAndIncreaseSeconds() {
            seconds++
            broadcastTimerUpdate()
        }

        private fun broadcastTimerUpdate() {
            val intent = createTimerUpdateEvent(seconds)
            sendBroadcast(intent)
        }

        private fun createTimerUpdateEvent(seconds: Double): Intent {
            val intent = Intent(TIMER_UPDATED)
            intent.putExtra(TIME_EXTRA, seconds)
            return intent
        }
    }

    companion object
    {
        const val TIMER_UPDATED = "timerUpdated"
        const val TIME_EXTRA = "timeExtra"
        const val TAG = "TimerService"
        const val ONE_SECOND: Long = 1000
    }
}