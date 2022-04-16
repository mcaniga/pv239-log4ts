package cz.muni.log4ts.ui.logEntries

import android.text.format.DateUtils
import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import cz.muni.log4ts.Log4TSApplication.Companion.appComponent
import cz.muni.log4ts.data.entities.LogEntry
import cz.muni.log4ts.databinding.ItemLogListBinding
import kotlinx.coroutines.launch
import javax.inject.Inject

class LogEntriesViewHolder(private val binding: ItemLogListBinding):
    RecyclerView.ViewHolder(binding.root) {
    @Inject
    lateinit var logEntriesViewHolderAction: LogEntriesViewHolderAction

    init {
        appComponent.injectLogEntriesViewHolderDeps(this)
    }

    fun bind(
        listItem: LogEntry,
        adapter: LogEntriesRecyclerViewAdapter,
        view: View,
        viewLifecycleOwner: LifecycleOwner,
        navController: NavController
    ) {
        binding.nameTextView.text = listItem.name
        binding.timeTextView.text = DateUtils.formatElapsedTime(listItem.loggedSeconds)
        binding.projectTextView.text = listItem.project
        binding.editButton.setOnClickListener {
            navController.navigate(
                LogEntriesFragmentDirections.actionLogEntriesFragmentToLogEntriesDetailFragment(listItem)
            )
        }
        binding.deleteButton.setOnClickListener {
            deleteLogEntry(adapter, view, viewLifecycleOwner, listItem)
        }
    }

    private fun deleteLogEntry(
        adapter: LogEntriesRecyclerViewAdapter,
        view: View,
        viewLifecycleOwner: LifecycleOwner,
        listItem: LogEntry
    ) {
        viewLifecycleOwner.lifecycleScope.launch {
            logEntriesViewHolderAction.deleteLogEntry(adapter, listItem, view)
        }
    }
}