package cz.muni.log4ts.ui.logEntries.detail

import androidx.core.widget.doAfterTextChanged
import com.wajahatkarim3.easyvalidation.core.view_ktx.validator
import cz.muni.log4ts.databinding.FragmentLogEditBinding
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LogDetailValidator @Inject constructor() {
    fun validateNameAfterInputChange(binding: FragmentLogEditBinding) {
        binding.nameInput.doAfterTextChanged { validateName(binding) }
    }

    fun validateName(binding: FragmentLogEditBinding): Boolean {
        return binding.nameInput.validator()
            .nonEmpty()
            .addErrorCallback {
                binding.nameInput.error = it
            }.check()
    }
}