package cz.muni.log4ts.ui.projects

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import cz.muni.log4ts.data.entities.Project
import cz.muni.log4ts.databinding.ItemLogListBinding
import cz.muni.log4ts.databinding.ItemProjectListBinding

class ProjectsRecyclerViewAdapter(val viewLifecycleOwner: LifecycleOwner, val view: View, val navController: NavController) : RecyclerView.Adapter<ProjectsViewHolder>() {

    private var listItems: MutableList<Project> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectsViewHolder {
        val binding = ItemProjectListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProjectsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProjectsViewHolder, position: Int) {
        holder.bind(listItems[position], this, view, viewLifecycleOwner, navController)
    }

    fun refreshList(items: List<Project>) {
        listItems = items.toMutableList()
        notifyDataSetChanged()
    }

    fun addProject(project: Project) {
        listItems.add(project)
        notifyDataSetChanged()
    }

    fun updateProject(project: Project) {
        removeProjectById(project)
        listItems.add(project)
        notifyDataSetChanged()
    }

    fun deleteProject(project: Project) {
        removeProjectById(project)
        notifyDataSetChanged()
    }

    private fun removeProjectById(project: Project) {
        listItems.removeIf{ it.id == project.id }
    }

    override fun getItemCount(): Int = listItems.size
}
