package cz.muni.log4ts.ui.logEntries.newLogEntry

import androidx.core.widget.doAfterTextChanged
import com.wajahatkarim3.easyvalidation.core.view_ktx.validator
import cz.muni.log4ts.databinding.FragmentLogNewBinding
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewLogEntryValidator @Inject constructor() {
    fun validateNameAfterInputChange(binding: FragmentLogNewBinding) {
        binding.nameInput.doAfterTextChanged { validateName(binding) }
    }

    fun validateName(binding: FragmentLogNewBinding): Boolean {
       return binding.nameInput.validator()
            .nonEmpty()
            .addErrorCallback {
                binding.nameInput.error = it
            }.check()
    }
}