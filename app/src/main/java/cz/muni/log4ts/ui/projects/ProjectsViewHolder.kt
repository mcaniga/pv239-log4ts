package cz.muni.log4ts.ui.projects

import android.util.Log
import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import cz.muni.log4ts.Log4TSApplication.Companion.appComponent
import cz.muni.log4ts.data.entities.Project
import cz.muni.log4ts.databinding.ItemProjectListBinding
import kotlinx.coroutines.launch
import okhttp3.internal.format
import javax.inject.Inject

class ProjectsViewHolder(
    private val binding: ItemProjectListBinding,
) : RecyclerView.ViewHolder(binding.root) {
    @Inject
    lateinit var projectsViewHolderAction: ProjectsViewHolderAction

    @Inject
    lateinit var extractor: ProjectsViewHolderExtractor

    private val TAG = ProjectsViewHolder::class.simpleName

    init {
        appComponent.injectProjectsViewHolderDeps(this)
    }

    fun bind(
        listItem: Project,
        adapter: ProjectsRecyclerViewAdapter,
        view: View,
        viewLifecycleOwner: LifecycleOwner
    ) {
        binding.nameEditView.setText(listItem.name)
        binding.nameEditView.isEnabled = false

        binding.editButton.setOnClickListener {
            updateProject(adapter, view, viewLifecycleOwner, listItem)
        }

        binding.deleteButton.setOnClickListener {
            deleteProject(adapter, view, viewLifecycleOwner, listItem)
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

    private fun updateProject(
        adapter: ProjectsRecyclerViewAdapter,
        view: View,
        viewLifecycleOwner: LifecycleOwner,
        listItem: Project
    ) {
        val isInputEnabled = binding.nameEditView.isEnabled
        Log.d(TAG, format("Editing project, input is enabled: %s", isInputEnabled))
        if (isInputEnabled) {
            viewLifecycleOwner.lifecycleScope.launch {
                val updatedProject = extractor.extractUpdatedProject(binding, listItem)
                projectsViewHolderAction.editProject(adapter, updatedProject, view)
            }
            binding.nameEditView.isEnabled = false
        } else {
            binding.nameEditView.isEnabled = true
        }
    }
}