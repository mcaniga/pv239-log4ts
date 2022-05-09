package cz.muni.log4ts.ui.projects

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import cz.muni.log4ts.Log4TSApplication.Companion.appComponent
import cz.muni.log4ts.dao.FirebaseAuthDao
import cz.muni.log4ts.data.entities.LogEntry
import cz.muni.log4ts.data.entities.NewProject
import cz.muni.log4ts.databinding.FragmentProjectsBinding
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProjectsFragment : Fragment() {
    @Inject
    lateinit var projectsAction: ProjectsFragmentAction

    @Inject
    lateinit var projectsFragmentExtractor: ProjectsFragmentExtractor

    @Inject
    lateinit var firebaseAuthDao: FirebaseAuthDao

    @Inject
    lateinit var projectValidator: ProjectValidator

    private lateinit var binding: FragmentProjectsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProjectsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        injectDependencies()
        val recyclerViewAdapter = ProjectsRecyclerViewAdapter(viewLifecycleOwner, view, findNavController())
        setRecyclerView(recyclerViewAdapter)
        createProjectOnCreateButtonClick(recyclerViewAdapter, view)
        getProjects(recyclerViewAdapter, view)
    }

    private fun getProjects(
        recyclerViewAdapter: ProjectsRecyclerViewAdapter,
        view: View
    ) {
        viewLifecycleOwner.lifecycleScope.launch {
            projectsAction.getProjectsOrShowError(
                "global",
                recyclerViewAdapter,
                view
            ) // TODO: get namespace from state
        }
    }

    private fun injectDependencies() {
        appComponent.injectProjectsFragmentDeps(this)
    }

    private fun setRecyclerView(recyclerViewAdapter: ProjectsRecyclerViewAdapter) {
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = recyclerViewAdapter
    }

    private fun createProjectOnCreateButtonClick(
        recyclerViewAdapter: ProjectsRecyclerViewAdapter,
        view: View
    ) {
        validateInputAfterChange()
        binding.createProjectButton.setOnClickListener {
            if (isInputValid()) {
                val newProject: NewProject = projectsFragmentExtractor.extractNewProject(
                    binding, "global" // TODO: from extract from state
                )
                viewLifecycleOwner.lifecycleScope.launch {
                    projectsAction.addProject(recyclerViewAdapter, newProject, view)
                }
            }
        }
    }

    private fun validateInputAfterChange() {
        projectValidator.validateNameAfterInputChange(binding)
    }

    private fun isInputValid(): Boolean {
        return projectValidator.validateName(binding)
    }
}