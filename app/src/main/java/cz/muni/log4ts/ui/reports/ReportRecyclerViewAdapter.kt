package cz.muni.log4ts.ui.reports

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import cz.muni.log4ts.R
import cz.muni.log4ts.databinding.FragmentReportBinding
import cz.muni.log4ts.ui.projects.ProjectsViewHolder

import cz.muni.log4ts.ui.reports.placeholder.PlaceholderContent.PlaceholderItem
//import cz.muni.log4ts.ui.reports.databinding.FragmentReportBinding

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */



import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import cz.muni.log4ts.data.entities.LogEntry
import cz.muni.log4ts.data.entities.Project
import cz.muni.log4ts.data.entities.ReportEntry
import cz.muni.log4ts.databinding.ItemLogListBinding
import cz.muni.log4ts.databinding.ItemProjectListBinding
import cz.muni.log4ts.databinding.ItemReportListBinding
import java.util.*

class ReportRecyclerViewAdapter(val viewLifecycleOwner: LifecycleOwner, val view: View, val navController: NavController) : RecyclerView.Adapter<ReportsViewHolder>() {

    private var listItems: MutableList<ReportEntry> = mutableListOf()

    lateinit var project: String
    lateinit var lowerBound: Date
    lateinit var upperBound: Date

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReportsViewHolder {
        val binding = ItemReportListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ReportsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReportsViewHolder, position: Int) {
        holder.bind(listItems[position], project, lowerBound, upperBound, this, view, viewLifecycleOwner, navController)
    }

    fun refreshList(items: List<ReportEntry>) {
        listItems = items.toMutableList()
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int = listItems.size
}
