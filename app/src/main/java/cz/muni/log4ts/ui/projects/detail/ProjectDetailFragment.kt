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
    }

    private fun editProjectOnSubmitButtonClick(
        view: View,
        project: Project
    ) {
        validateInputAfterInputChange()
        binding.submitButton.setOnClickListener {
            if (isInputValid()){
                editProject(view, project)
            }
        }
    }

    private fun validateInputAfterInputChange() {
        projectDetailValidator.validateNameAfterInputChange(binding)
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
