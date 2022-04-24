package cz.muni.log4ts.ui.projects.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import cz.muni.log4ts.Log4TSApplication
import cz.muni.log4ts.R
import cz.muni.log4ts.data.entities.Project
import cz.muni.log4ts.databinding.FragmentProjectEditBinding
import cz.muni.log4ts.repository.FirebaseProjectRepository
import cz.muni.log4ts.util.ErrorHandler
import cz.muni.log4ts.util.ErrorHandler.StaticMethods.safelyNavigateUp
import kotlinx.coroutines.launch
import javax.inject.Inject


class ProjectDetailFragment : Fragment() {
    @Inject
    lateinit var logEntriesDetailAction: ProjectDetailFragmentAction

    @Inject
    lateinit var logEntriesDetailFragmentExtractor: ProjectDetailFragmentExtractor

    @Inject
    lateinit var firebaseProjectRepository: FirebaseProjectRepository

    private val TAG = ProjectDetailFragment::class.simpleName

    private lateinit var binding: FragmentProjectEditBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProjectEditBinding.inflate(LayoutInflater.from(context), container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log4TSApplication.appComponent.injectProjectDetailFragmentDeps(this)
        val project: Project = ProjectDetailFragmentArgs.fromBundle(requireArguments()).item

        binding.toolbar.setNavigationIcon(R.drawable.ic_arrow_back)
        binding.toolbar.setNavigationOnClickListener {
            safelyNavigateUp(findNavController(), TAG, view)
        }

        binding.nameInput.setText(project.name)

        binding.submitButton.setOnClickListener {
            editProject(view, project)
        }
    }

    private fun editProject(
        view: View,
        oldProject: Project
    ) {
        val updatedProject: Project = logEntriesDetailFragmentExtractor.extractUpdatedProject(
            binding, oldProject
        )
        viewLifecycleOwner.lifecycleScope.launch {
            logEntriesDetailAction.editProject(updatedProject, view)
        }
    }
}
