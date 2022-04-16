package cz.muni.log4ts.ui.logEntries

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import cz.muni.log4ts.data.ui.LogEntriesItem
import cz.muni.log4ts.databinding.ItemLogListBinding

class LogEntriesRecyclerViewAdapter(val viewLifecycleOwner: LifecycleOwner, val view: View) : RecyclerView.Adapter<LogEntriesViewHolder>() {

    private var listItems: MutableList<LogEntriesItem> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LogEntriesViewHolder {
        val binding = ItemLogListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LogEntriesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LogEntriesViewHolder, position: Int) {
        holder.bind(listItems[position], this, view, viewLifecycleOwner)
    }

    fun refreshList(items: List<LogEntriesItem>) {
        listItems = items.toMutableList()
        notifyDataSetChanged()
    }

    fun addLogEntry(logEntry: LogEntriesItem) {
        listItems.add(logEntry)
        notifyDataSetChanged()
    }

    fun updateLogEntry(logEntry: LogEntriesItem) {
        removeLogEntryById(logEntry)
        listItems.add(logEntry)
        notifyDataSetChanged()
    }

    fun deleteLogEntry(logEntry: LogEntriesItem) {
        removeLogEntryById(logEntry)
        notifyDataSetChanged()
    }

    private fun removeLogEntryById(logEntry: LogEntriesItem) {
        listItems.removeIf{ it.id == logEntry.id }
    }

    override fun getItemCount(): Int = listItems.size
}
