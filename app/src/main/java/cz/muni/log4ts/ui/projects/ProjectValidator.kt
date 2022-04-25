package cz.muni.log4ts.ui.projects

import androidx.core.widget.doAfterTextChanged
import com.wajahatkarim3.easyvalidation.core.view_ktx.validator
import cz.muni.log4ts.databinding.FragmentProjectsBinding
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProjectValidator @Inject constructor() {
    fun validateNameAfterInputChange(binding: FragmentProjectsBinding) {
        binding.newProjectInput.doAfterTextChanged { validateName(binding) }
    }

    fun validateName(binding: FragmentProjectsBinding): Boolean {
         return binding.newProjectInput.validator()
            .nonEmpty()
            .addErrorCallback {
                binding.newProjectInput.error = it
            }.check()
    }
}