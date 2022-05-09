package cz.muni.log4ts.ui.reports.detail

import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import cz.muni.log4ts.data.entities.LogEntry
import cz.muni.log4ts.data.entities.ReportEntry
import cz.muni.log4ts.databinding.ItemReportListBinding

class ReportsDetailViewHolder(
    private val binding: ItemReportListBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        listItem: LogEntry,
        adapter: ReportDetailRecyclerViewAdapter,
        view: View,
        viewLifecycleOwner: LifecycleOwner,
        navController: NavController
    ) {
        inicializeLogEntryInput(listItem)
    }

    private fun inicializeLogEntryInput(listItem: LogEntry) {
        binding.nameTextView.text = listItem.name
//        binding.nameTextView.isEnabled = false
        binding.timeTextView.text = listItem.loggedSeconds.toString()
    }
}