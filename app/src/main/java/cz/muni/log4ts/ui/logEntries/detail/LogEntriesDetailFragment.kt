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
import cz.muni.log4ts.databinding.FragmentLogEditBinding
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

    private lateinit var binding: FragmentLogEditBinding

    private val TAG = LogEntriesDetailFragment::class.simpleName

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
        Log4TSApplication.appComponent.injectLogEntriesDetailFragmentDeps(this)
        val logEntry: LogEntry = LogEntriesDetailFragmentArgs.fromBundle(requireArguments()).item

        binding.toolbar.setNavigationIcon(R.drawable.ic_arrow_back)
        binding.toolbar.setNavigationOnClickListener {
            safelyNavigateUp(findNavController(), TAG, view)
        }

        binding.nameInput.setText(logEntry.name)
        viewLifecycleOwner.lifecycleScope.launch {
            val projectSpinnerAdapter = projectSpinnerAdapterFactory.makeProjectSpinnerAdapter(
                requireContext(),
                firebaseProjectRepository
            )
            binding.projectsSpinner.adapter = projectSpinnerAdapter
        }

        binding.submitButton.setOnClickListener {
            editLogEntry(view, logEntry)
        }
    }

    private fun editLogEntry(
        view: View,
        oldLogEntry: LogEntry
    ) {
        val updatedLogEntry: LogEntry = logEntriesDetailFragmentExtractor.extractUpdatedLogEntry(
            binding, oldLogEntry
        )
        viewLifecycleOwner.lifecycleScope.launch {
            logEntriesDetailAction.editLogEntry(updatedLogEntry, view)
        }
    }
}
