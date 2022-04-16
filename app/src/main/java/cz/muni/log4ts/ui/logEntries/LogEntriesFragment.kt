package cz.muni.log4ts.ui.logEntries

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import cz.muni.log4ts.Log4TSApplication.Companion.appComponent
import cz.muni.log4ts.dao.FirebaseAuthDao
import cz.muni.log4ts.data.entities.NewLogEntry
import cz.muni.log4ts.databinding.FragmentLogEntriesBinding
import kotlinx.coroutines.launch
import javax.inject.Inject

class LogEntriesFragment : Fragment() {
    @Inject
    lateinit var logEntriesAction: LogEntriesFragmentAction

    @Inject
    lateinit var logEntriesFragmentExtractor: LogEntriesFragmentExtractor

    @Inject
    lateinit var firebaseAuthDao: FirebaseAuthDao

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

        val recyclerViewAdapter = LogEntriesRecyclerViewAdapter()

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = recyclerViewAdapter

        binding.logButton.setOnClickListener {
            val userId: String = firebaseAuthDao.getCurrentUserId()!! // TODO: remove !! safely

            // TODO: extract namespace from state
            // TODO: add project picker and extract project from the picker
            val newLogEntry: NewLogEntry = logEntriesFragmentExtractor.extractNewLogEntry(
                binding, userId, "global", "piskanica"
            )
            viewLifecycleOwner.lifecycleScope.launch {
                logEntriesAction.addLogEntry(recyclerViewAdapter, newLogEntry, view)
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            val userId: String = firebaseAuthDao.getCurrentUserId()!! // TODO: remove !! safely
            logEntriesAction.getLogEntriesOrShowError(userId, recyclerViewAdapter, view)
        }
    }
}