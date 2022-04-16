package cz.muni.log4ts.ui.logEntries.detail

import android.R as RA
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import cz.muni.log4ts.Log4TSApplication
import cz.muni.log4ts.R
import cz.muni.log4ts.data.entities.LogEntry
import cz.muni.log4ts.databinding.FragmentLogEditBinding
import cz.muni.log4ts.repository.FirebaseProjectRepository
import kotlinx.coroutines.launch
import javax.inject.Inject


class LogEntriesDetailFragment : Fragment() {
    @Inject
    lateinit var logEntriesDetailAction: LogEntriesDetailFragmentAction

    @Inject
    lateinit var logEntriesDetailFragmentExtractor: LogEntriesDetailFragmentExtractor

    @Inject
    lateinit var firebaseProjectRepository: FirebaseProjectRepository


    private lateinit var binding: FragmentLogEditBinding

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
            findNavController().navigateUp()
        }

        binding.nameInput.setText(logEntry.name)
        viewLifecycleOwner.lifecycleScope.launch {
            val projects =  firebaseProjectRepository.getAllProjectsInNamespace("global") // TODO: get namespace from state
            val projectNames: List<String> = projects.map { it.name }

            val spinnerArrayAdapter: ArrayAdapter<String> = ArrayAdapter<String>(
                requireContext(),
                RA.layout.simple_spinner_item,
                projectNames
            ).also {
                it.setDropDownViewResource(RA.layout.simple_spinner_dropdown_item)
            }
            binding.projectsSpinner.adapter = spinnerArrayAdapter
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
