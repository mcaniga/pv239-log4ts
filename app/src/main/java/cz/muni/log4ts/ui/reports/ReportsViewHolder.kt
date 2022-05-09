package cz.muni.log4ts.ui.reports

import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import cz.muni.log4ts.data.dto.ReportDetailDto
import cz.muni.log4ts.data.entities.ReportEntry
import cz.muni.log4ts.databinding.ItemReportListBinding
import java.util.*

class ReportsViewHolder(
    private val binding: ItemReportListBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        listItem: ReportEntry,
        project: String,
        lowerBound: Date,
        upperBound: Date,
        adapter: ReportRecyclerViewAdapter,
        view: View,
        viewLifecycleOwner: LifecycleOwner,
        navController: NavController
    ) {
        inicializeProjectNameInput(listItem)
        navigateToDetailOnEditButtonClick(navController, listItem, project, lowerBound, upperBound)
    }

    private fun inicializeProjectNameInput(listItem: ReportEntry) {
        binding.nameTextView.text = listItem.username
        binding.timeTextView.text = listItem.seconds.toString()
    }

    private fun navigateToDetailOnEditButtonClick(
        navController: NavController,
        listItem: ReportEntry,
        project: String,
        lowerBound: Date,
        upperBound: Date
    ) {
        binding.cardContainer.setOnClickListener {
            navController.navigate(
                ReportFragmentDirections.actionReportsFragmentToReportsDetailFragment(
                    ReportDetailDto(
                        username = listItem.username,
                        minutes = listItem.seconds,
                        project = project,
                        userId = listItem.userId,
                        lowerBound = lowerBound,
                        upperBound = upperBound
                    )
                )
            )
        }
    }
}