package cz.muni.log4ts.ui.logEntries.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import cz.muni.log4ts.Log4TSApplication
import cz.muni.log4ts.R
import cz.muni.log4ts.data.entities.LogEntry
import cz.muni.log4ts.data.ui.PickedDateTime
import cz.muni.log4ts.databinding.FragmentLogEditBinding
import cz.muni.log4ts.extension.toPickedDateTime
import cz.muni.log4ts.extension.toPrettyString
import cz.muni.log4ts.extension.toTimestamp
import cz.muni.log4ts.repository.FirebaseProjectRepository
import cz.muni.log4ts.ui.projects.detail.ProjectSpinnerAdapterFactory
import cz.muni.log4ts.util.ErrorHandler.StaticMethods.safelyNavigateUp
import kotlinx.coroutines.launch
import javax.inject.Inject


class LogEntriesDetailFragment : Fragment() {
    @Inject
    lateinit var logEntriesDetailAction: LogEntriesDetailFragmentAction

    @Inject
    lateinit var logEntriesDetailFragmentExtractor: LogEntriesDetailFragmentExtractor

    @Inject
    lateinit var firebaseProjectRepository: FirebaseProjectRepository

    @Inject
    lateinit var projectSpinnerAdapterFactory: ProjectSpinnerAdapterFactory

    @Inject
    lateinit var logDetailValidator: LogDetailValidator

    @Inject
    lateinit var logEntriesDateTimePickerHandler: LogEntriesDateTimePickerHandler

    private lateinit var binding: FragmentLogEditBinding

    private val TAG = LogEntriesDetailFragment::class.simpleName

    private lateinit var pickedStartTime: PickedDateTime
    private lateinit var pickedEndTime: PickedDateTime

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLogEditBinding.inflate(LayoutInflater.from(context), container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        injectDependencies()
        val logEntry: LogEntry = extractLogEntryFromArgs()
        setBackButton(view)
        inicializeLogEntryName(logEntry)
        initializeProjectSpinner()
        initializeLogTimes(logEntry)
        initializePickedTime(logEntry)
        binding.editStartTimeButton.setOnClickListener {
            logEntriesDateTimePickerHandler.setLogTime(
                requireContext(), view, pickedStartTime, null,
                pickedEndTime, binding.startDatetimeTextView
            )
            binding.startDatetimeTextView.text = pickedStartTime.toTimestamp().toPrettyString()
        }
        binding.editEndTimeButton.setOnClickListener {
            logEntriesDateTimePickerHandler.setLogTime(
                requireContext(), view, pickedEndTime, pickedStartTime,
                null, binding.endDatetimeTextView
            )
        }
        editLogEntryOnSubmitButtonClick(view, logEntry)
    }

    private fun initializePickedTime(logEntry: LogEntry) {
        pickedStartTime = logEntry.startTime.toPickedDateTime()
        pickedEndTime = logEntry.endTime.toPickedDateTime()
    }

    private fun initializeLogTimes(logEntry: LogEntry) {
        binding.startDatetimeTextView.text = logEntry.startTime.toPrettyString()
        binding.endDatetimeTextView.text = logEntry.endTime.toPrettyString()
    }

    private fun editLogEntryOnSubmitButtonClick(
        view: View,
        logEntry: LogEntry
    ) {
        validateInputAfterInputChange()
        binding.submitButton.setOnClickListener {
            if (isInputValid()) {
                editLogEntry(view, logEntry)
            }
        }
    }

    private fun validateInputAfterInputChange() {
        logDetailValidator.validateNameAfterInputChange(binding)
    }

    private fun isInputValid(): Boolean {
        return logDetailValidator.validateName(binding)
    }

    private fun initializeProjectSpinner() {
        viewLifecycleOwner.lifecycleScope.launch {
            val projectSpinnerAdapter = projectSpinnerAdapterFactory.makeProjectSpinnerAdapter(
                requireContext(),
                firebaseProjectRepository
            )
            binding.projectsSpinner.adapter = projectSpinnerAdapter
        }
    }

    private fun inicializeLogEntryName(logEntry: LogEntry) {
        binding.nameInput.setText(logEntry.name)
    }

    private fun setBackButton(view: View) {
        binding.toolbar.setNavigationIcon(R.drawable.ic_arrow_back)
        binding.toolbar.setNavigationOnClickListener {
            safelyNavigateUp(findNavController(), TAG, view)
        }
    }

    private fun extractLogEntryFromArgs() =
        LogEntriesDetailFragmentArgs.fromBundle(requireArguments()).item

    private fun injectDependencies() {
        Log4TSApplication.appComponent.injectLogEntriesDetailFragmentDeps(this)
    }

    private fun editLogEntry(
        view: View,
        oldLogEntry: LogEntry
    ) {
        val updatedLogEntry: LogEntry = logEntriesDetailFragmentExtractor.extractUpdatedLogEntry(
            binding, oldLogEntry, pickedStartTime, pickedEndTime
        )
        viewLifecycleOwner.lifecycleScope.launch {
            logEntriesDetailAction.editLogEntry(updatedLogEntry, view)
        }
    }
}
