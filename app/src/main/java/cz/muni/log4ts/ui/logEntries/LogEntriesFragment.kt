package cz.muni.log4ts.ui.logEntries

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import cz.muni.log4ts.Log4TSApplication.Companion.appComponent
import cz.muni.log4ts.dao.FirebaseAuthDao
import cz.muni.log4ts.data.entities.NewLogArg
import cz.muni.log4ts.databinding.FragmentLogEntriesBinding
import cz.muni.log4ts.service.TimerService
import kotlinx.coroutines.launch
import javax.inject.Inject


class LogEntriesFragment : Fragment() {
    @Inject
    lateinit var logEntriesAction: LogEntriesFragmentAction

    @Inject
    lateinit var logEntriesFragmentExtractor: LogEntriesFragmentExtractor

    @Inject
    lateinit var logTimerUIHandler: LogTimerUIHandler

    @Inject
    lateinit var firebaseAuthDao: FirebaseAuthDao

    private var logTimerState = LogTimerState()

    private lateinit var binding: FragmentLogEntriesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLogEntriesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        appComponent.injectLogEntriesFragmentDeps(this)

        val recyclerViewAdapter =
            LogEntriesRecyclerViewAdapter(viewLifecycleOwner, view, findNavController())
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = recyclerViewAdapter

        binding.logButton.setOnClickListener {
            if (logTimerState.isTimerRunning()) {
                endLogging()
            } else {
                startLogging()
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            val userId: String = firebaseAuthDao.getCurrentUserId()!!
            logEntriesAction.getLogEntriesOrShowError(userId, recyclerViewAdapter, view)
        }

        logTimerState.serviceIntent = Intent(requireContext(), TimerService::class.java)
        subscribeToTimerUpdatedEvent()

        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    // Handle the back button event
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }

    private fun subscribeToTimerUpdatedEvent() {
        requireActivity().registerReceiver(updateTime, IntentFilter(TimerService.TIMER_UPDATED))
    }

    private val updateTime: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            logTimerState.time = intent.getDoubleExtra(TimerService.TIME_EXTRA, 0.0)
            binding.logDurationTextView.text = logTimerState.getTimeString()
            if (logTimerState.isTimerRunning()) {
                binding.logButton.text = "Stop"
            }
        }
    }

    private fun startLogging() {
        logTimerUIHandler.startTimer(logTimerState, binding, requireActivity())
    }

    private fun endLogging() {
        val elapsedSeconds = logTimerUIHandler.stopTimer(logTimerState, binding, requireActivity())
        navigateToNewLogFragment(elapsedSeconds)
    }

    private fun navigateToNewLogFragment(elapsedSeconds: Long) {
        findNavController().navigate(
            LogEntriesFragmentDirections.actionLogEntriesFragmentToNewLogEntryFragment(
                NewLogArg(elapsedSeconds)
            )
        )
    }
}