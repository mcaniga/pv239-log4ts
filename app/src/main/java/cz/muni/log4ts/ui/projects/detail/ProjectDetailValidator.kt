package cz.muni.log4ts.ui.projects.detail

import androidx.core.widget.doAfterTextChanged
import com.wajahatkarim3.easyvalidation.core.view_ktx.validator
import cz.muni.log4ts.databinding.FragmentProjectEditBinding
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProjectDetailValidator @Inject constructor() {
    fun validateNameAfterInputChange(binding: FragmentProjectEditBinding) {
        binding.nameInput.doAfterTextChanged { validateName(binding) }
    }

    fun validateName(binding: FragmentProjectEditBinding): Boolean {
        return binding.nameInput.validator()
            .nonEmpty()
            .addErrorCallback {
                binding.nameInput.error = it
            }.check()
    }
}