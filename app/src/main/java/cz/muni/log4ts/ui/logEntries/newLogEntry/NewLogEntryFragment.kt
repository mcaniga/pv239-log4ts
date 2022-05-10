package cz.muni.log4ts.ui.logEntries.newLogEntry

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import cz.muni.log4ts.Log4TSApplication.Companion.appComponent
import cz.muni.log4ts.R
import cz.muni.log4ts.dao.FirebaseAuthDao
import cz.muni.log4ts.data.entities.NewLogEntry
import cz.muni.log4ts.databinding.FragmentLogNewBinding
import cz.muni.log4ts.repository.FirebaseProjectRepository
import cz.muni.log4ts.ui.projects.detail.ProjectSpinnerAdapterFactory
import cz.muni.log4ts.util.ErrorHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewLogEntryFragment : Fragment() {
    @Inject
    lateinit var newLogEntryAction: NewLogEntryFragmentAction

    @Inject
    lateinit var newLogEntryFragmentExtractor: NewLogEntryFragmentExtractor

    @Inject
    lateinit var firebaseProjectRepository: FirebaseProjectRepository

    @Inject
    lateinit var projectSpinnerAdapterFactory: ProjectSpinnerAdapterFactory

    @Inject
    lateinit var newLogEntryValidator: NewLogEntryValidator

    @Inject
    lateinit var firebaseAuthDao: FirebaseAuthDao

    private lateinit var binding: FragmentLogNewBinding

    private val TAG = NewLogEntryFragment::class.simpleName

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLogNewBinding.inflate(LayoutInflater.from(context), container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        injectDependencies()
        val elapsedSeconds: Long = extractElapsedSecondsFromArgs()
        setBackButton(view)
        populateProjectSpinner()
        addLogEntryOnSubmitButtonClick(view, elapsedSeconds)
    }

    private fun addLogEntryOnSubmitButtonClick(view: View, elapsedSeconds: Long) {
        validateNewLogEntryInputAfterInputChange()
        binding.submitButton.setOnClickListener {
            if (isNewLogEntryInputValid()) {
                addLogEntry(view, elapsedSeconds)
            }
        }
    }

    private fun validateNewLogEntryInputAfterInputChange() {
        newLogEntryValidator.validateNameAfterInputChange(binding)
    }

    private fun isNewLogEntryInputValid(): Boolean {
        return newLogEntryValidator.validateName(binding)
    }

    private fun populateProjectSpinner() {
        viewLifecycleOwner.lifecycleScope.launch {
            val projectSpinnerAdapter = projectSpinnerAdapterFactory.makeProjectSpinnerAdapter(
                requireContext(),
                firebaseProjectRepository
            )
            binding.projectsSpinner.adapter = projectSpinnerAdapter
        }
    }

    private fun setBackButton(view: View) {
        binding.toolbar.setNavigationIcon(R.drawable.ic_arrow_back)
        binding.toolbar.setNavigationOnClickListener {
            ErrorHandler.safelyNavigateUp(findNavController(), TAG, view)
        }
    }

    private fun extractElapsedSecondsFromArgs() =
        NewLogEntryFragmentArgs.fromBundle(requireArguments()).item.elapsedSeconds

    private fun injectDependencies() {
        appComponent.injectNewLogEntryFragmentDeps(this)
    }

    private fun addLogEntry(
        view: View,
        elapsedSeconds: Long
    ) {
        val userId: String = firebaseAuthDao.getCurrentUserId()!!
        val newLogEntry: NewLogEntry = newLogEntryFragmentExtractor.extractNewLogEntry(
            binding, userId, "global", elapsedSeconds
        )

        val navController = findNavController()
        viewLifecycleOwner.lifecycleScope.launch {
            newLogEntryAction.addLogEntry(navController, newLogEntry, view)
        }
    }
}
