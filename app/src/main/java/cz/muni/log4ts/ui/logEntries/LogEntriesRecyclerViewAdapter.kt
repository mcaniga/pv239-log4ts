package cz.muni.log4ts.ui.logEntries

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cz.muni.log4ts.data.ui.LogEntriesItem
import cz.muni.log4ts.databinding.ItemLogListBinding

class LogEntriesRecyclerViewAdapter : RecyclerView.Adapter<LogEntriesViewHolder>() {

    private var listItems: MutableList<LogEntriesItem> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LogEntriesViewHolder {
        val binding = ItemLogListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LogEntriesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LogEntriesViewHolder, position: Int) {
        holder.bind(listItems[position])
    }

    fun submitList(newListItems: List<LogEntriesItem>) {
        listItems = newListItems.toMutableList()
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = listItems.size
}
