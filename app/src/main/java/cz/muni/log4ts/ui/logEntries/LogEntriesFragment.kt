package cz.muni.log4ts.ui.logEntries

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import cz.muni.log4ts.databinding.FragmentLogEntriesBinding
import cz.muni.log4ts.repository.FirebaseLogRepository
import cz.muni.log4ts.repository.LogRepositoryInterface
import cz.muni.log4ts.util.ErrorHandler.StaticMethods.showErrorSnackbar
import kotlinx.coroutines.launch

class LogEntriesFragment : Fragment() {

    private lateinit var binding: FragmentLogEntriesBinding
    private val TAG = LogEntriesFragment::class.simpleName

    // TODO: move repository up the component hierarchy, LogEntriesFragment will get its entries as an argument
    private val logRepository: LogRepositoryInterface by lazy {
        FirebaseLogRepository()
    }

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

        viewLifecycleOwner.lifecycleScope.launch {
            updateLogEntriesOrShowError(recyclerViewAdapter)
        }
    }

    private suspend fun updateLogEntriesOrShowError(recyclerViewAdapter: LogEntriesRecyclerViewAdapter) {
        try {
            recyclerViewAdapter.submitList(logRepository.getLogEntriesItems())
        } catch (e: Exception) {
            showErrorSnackbar(e, TAG, "Fetching user data failed...", view)
        }
    }
}