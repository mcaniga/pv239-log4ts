package cz.muni.log4ts.ui.logEntries

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import cz.muni.log4ts.databinding.FragmentLogEntriesBinding
import cz.muni.log4ts.repository.FakeLogRepository
import cz.muni.log4ts.repository.LogRepositoryInterface

class LogEntriesFragment : Fragment() {

    private lateinit var binding: FragmentLogEntriesBinding

    // TODO: move repository up the component hierarchy, LogEntriesFragment will get its entries as an argument
    private val logRepository: LogRepositoryInterface by lazy {
        FakeLogRepository()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentLogEntriesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerViewAdapter = LogEntriesRecyclerViewAdapter()

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = recyclerViewAdapter

        recyclerViewAdapter.submitList(logRepository.getLogEntriesItems())
    }
}