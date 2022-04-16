package cz.muni.log4ts.ui.logEntries

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import cz.muni.log4ts.data.entities.LogEntry
import cz.muni.log4ts.databinding.ItemLogListBinding

class LogEntriesRecyclerViewAdapter(val viewLifecycleOwner: LifecycleOwner, val view: View, val navController: NavController) : RecyclerView.Adapter<LogEntriesViewHolder>() {

    private var listItems: MutableList<LogEntry> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LogEntriesViewHolder {
        val binding = ItemLogListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LogEntriesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LogEntriesViewHolder, position: Int) {
        holder.bind(listItems[position], this, view, viewLifecycleOwner, navController)
    }

    fun refreshList(items: List<LogEntry>) {
        listItems = items.toMutableList()
        notifyDataSetChanged()
    }

    fun addLogEntry(logEntry: LogEntry) {
        listItems.add(logEntry)
        notifyDataSetChanged()
    }

    fun updateLogEntry(logEntry: LogEntry) {
        removeLogEntryById(logEntry)
        listItems.add(logEntry)
        notifyDataSetChanged()
    }

    fun deleteLogEntry(logEntry: LogEntry) {
        removeLogEntryById(logEntry)
        notifyDataSetChanged()
    }

    private fun removeLogEntryById(logEntry: LogEntry) {
        listItems.removeIf{ it.id == logEntry.id }
    }

    override fun getItemCount(): Int = listItems.size
}
