package cz.muni.log4ts.ui.logEntries

import android.text.format.DateUtils
import androidx.recyclerview.widget.RecyclerView
import cz.muni.log4ts.data.ui.LogEntriesItem
import cz.muni.log4ts.databinding.ItemLogListBinding

class LogEntriesViewHolder(private val binding: ItemLogListBinding)
    : RecyclerView.ViewHolder(binding.root) {

    fun bind(listItem: LogEntriesItem) {
        binding.nameTextView.text = listItem.name
        binding.timeTextView.text = DateUtils.formatElapsedTime(listItem.loggedSeconds)
        binding.projectTextView.text = listItem.project
    }
}