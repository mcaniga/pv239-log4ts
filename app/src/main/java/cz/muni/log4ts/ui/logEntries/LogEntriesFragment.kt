package cz.muni.log4ts.ui.logEntries

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import cz.muni.log4ts.data.entities.NewLogEntry
import cz.muni.log4ts.databinding.FragmentLogEntriesBinding
import kotlinx.coroutines.launch

// TODO: Use DI
class LogEntriesFragment : Fragment() {
    private val logEntriesAction = LogEntriesFragmentAction()
    private val logEntriesFragmentExtractor = LogEntriesFragmentExtractor()

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

        val recyclerViewAdapter = LogEntriesRecyclerViewAdapter()

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = recyclerViewAdapter

        binding.logButton.setOnClickListener {
            val newLogEntry: NewLogEntry = logEntriesFragmentExtractor.extractNewLogEntry(
                binding, "1", "lidl", "piskanica" // TODO: from extract from state
            )
            viewLifecycleOwner.lifecycleScope.launch {
                logEntriesAction.addLogEntry(recyclerViewAdapter, newLogEntry)
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            logEntriesAction.getLogEntriesOrShowError(recyclerViewAdapter, view)
        }
    }
}