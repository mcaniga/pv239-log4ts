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
        appComponent.injectProjectsFragmentDeps(this)

        val recyclerViewAdapter = ProjectsRecyclerViewAdapter(viewLifecycleOwner, view, findNavController())

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = recyclerViewAdapter

        binding.createProjectButton.setOnClickListener {
            val newProject: NewProject = projectsFragmentExtractor.extractNewProject(
                binding, "global" // TODO: from extract from state
            )
            viewLifecycleOwner.lifecycleScope.launch {
                projectsAction.addProject(recyclerViewAdapter, newProject, view)
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            projectsAction.getProjectsOrShowError("global", recyclerViewAdapter, view) // TODO: get namespace from state
        }
    }
}