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

    @Inject
    lateinit var projectDetailValidator: ProjectDetailValidator

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
        injectDependencies()
        val project: Project = extractProjectFromArgs()
        setBackButton(view)
        inicializeProjectName(project)
        editProjectOnSubmitButtonClick(view, project)
        addUserToProjectOnAddButtonClick(view, project)
        removeUserFromProjectOnRemoveButtonClick(view, project)
    }

    private fun editProjectOnSubmitButtonClick(
        view: View,
        project: Project
    ) {

        binding.submitButton.setOnClickListener {
            val updatedProject: Project = logEntriesDetailFragmentExtractor.extractUpdatedProject(
                binding, project
            )

            if (isInputValid()){
                editProject(view, updatedProject)
            }
        }
    }

    private fun addUserToProjectOnAddButtonClick(
        view: View,
        project: Project
    ) {

        binding.addUserButton.setOnClickListener {
            validateEmailAfterInputChange()
            val email: String = binding.emailInput.text.toString()
            viewLifecycleOwner.lifecycleScope.launch {
                logEntriesDetailAction.editProjectAddUser(project, email, view)
            }
        }
    }

    private fun removeUserFromProjectOnRemoveButtonClick(
        view: View,
        project: Project
    ) {
        binding.removeUserButton.setOnClickListener {
            validateEmailAfterInputChange()
            val email: String = binding.emailInput.text.toString()
            viewLifecycleOwner.lifecycleScope.launch {
                logEntriesDetailAction.editProjectRemoveUser(project, email, view)
            }
        }
    }

    private fun validateEmailAfterInputChange() {
        projectDetailValidator.validateEmailAfterInputChange(binding)
    }

    private fun isInputValid(): Boolean {
        return projectDetailValidator.validateName(binding)
    }

    private fun inicializeProjectName(project: Project) {
        binding.nameInput.setText(project.name)
    }

    private fun injectDependencies() {
        Log4TSApplication.appComponent.injectProjectDetailFragmentDeps(this)
    }

    private fun extractProjectFromArgs() =
        ProjectDetailFragmentArgs.fromBundle(requireArguments()).item

    private fun setBackButton(view: View) {
        binding.toolbar.setNavigationIcon(R.drawable.ic_arrow_back)
        binding.toolbar.setNavigationOnClickListener {
            safelyNavigateUp(findNavController(), TAG, view)
        }
    }

    private fun editProject(
        view: View,
        updatedProject: Project
    ) {
        val navController = findNavController()
        viewLifecycleOwner.lifecycleScope.launch {
            logEntriesDetailAction.editProject(navController, updatedProject, view)
        }
    }
}
