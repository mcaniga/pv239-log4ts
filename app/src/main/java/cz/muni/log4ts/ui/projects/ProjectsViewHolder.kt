package cz.muni.log4ts.ui.projects

import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import cz.muni.log4ts.Log4TSApplication.Companion.appComponent
import cz.muni.log4ts.data.entities.Project
import cz.muni.log4ts.databinding.ItemProjectListBinding
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProjectsViewHolder(
    private val binding: ItemProjectListBinding,
) : RecyclerView.ViewHolder(binding.root) {
    @Inject
    lateinit var projectsViewHolderAction: ProjectsViewHolderAction

    @Inject
    lateinit var extractor: ProjectsViewHolderExtractor

    init {
        appComponent.injectProjectsViewHolderDeps(this)
    }

    fun bind(
        listItem: Project,
        adapter: ProjectsRecyclerViewAdapter,
        view: View,
        viewLifecycleOwner: LifecycleOwner,
        navController: NavController
    ) {
        inicializeProjectNameInput(listItem)
        navigateToDetailOnEditButtonClick(navController, listItem)
        deleteProjectOnDeleteButtonClick(adapter, view, viewLifecycleOwner, listItem)
    }

    private fun inicializeProjectNameInput(listItem: Project) {
        binding.nameEditView.setText(listItem.name)
        binding.nameEditView.isEnabled = false
    }

    private fun deleteProjectOnDeleteButtonClick(
        adapter: ProjectsRecyclerViewAdapter,
        view: View,
        viewLifecycleOwner: LifecycleOwner,
        listItem: Project
    ) {
        binding.deleteButton.setOnClickListener {
            deleteProject(adapter, view, viewLifecycleOwner, listItem)
        }
    }

    private fun navigateToDetailOnEditButtonClick(
        navController: NavController,
        listItem: Project
    ) {
        binding.editButton.setOnClickListener {
            navController.navigate(
                ProjectsFragmentDirections.actionProjectsFragmentToProjectDetailFragment(listItem)
            )
        }
    }

    private fun deleteProject(
        adapter: ProjectsRecyclerViewAdapter,
        view: View,
        viewLifecycleOwner: LifecycleOwner,
        listItem: Project
    ) {
        viewLifecycleOwner.lifecycleScope.launch {
            projectsViewHolderAction.deleteProject(adapter, listItem, view)
        }
    }
}