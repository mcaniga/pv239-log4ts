package cz.muni.log4ts.ui.reports.detail

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

//import cz.muni.log4ts.ui.reports.databinding.FragmentReportBinding

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */



import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import cz.muni.log4ts.data.entities.LogEntry
import cz.muni.log4ts.data.entities.ReportEntry
import cz.muni.log4ts.databinding.ItemReportListBinding

class ReportDetailRecyclerViewAdapter(val viewLifecycleOwner: LifecycleOwner, val view: View, val navController: NavController) : RecyclerView.Adapter<ReportsDetailViewHolder>() {

    var listItems: MutableList<LogEntry> = mutableListOf()

    private lateinit var project: String

    private lateinit var userId: String


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReportsDetailViewHolder {
        val binding = ItemReportListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ReportsDetailViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReportsDetailViewHolder, position: Int) {
        holder.bind(listItems[position], this, view, viewLifecycleOwner, navController)
    }

    fun refreshList(items: List<LogEntry>) {
        listItems = items.toMutableList()
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = listItems.size
}
