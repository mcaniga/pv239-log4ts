package cz.muni.log4ts.ui.auth.login

import androidx.core.widget.doAfterTextChanged
import com.wajahatkarim3.easyvalidation.core.view_ktx.validator
import cz.muni.log4ts.databinding.FragmentLoginBinding
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginValidator @Inject constructor() {
    fun validateEmailAfterInputChange(binding: FragmentLoginBinding) {
        binding.emailInput.doAfterTextChanged { validateEmail(binding) }
    }

    fun validateEmail(binding: FragmentLoginBinding): Boolean {
        return binding.emailInput.validator()
            .nonEmpty()
            .validEmail()
            .addErrorCallback {
                binding.emailInput.error = it
            }.check()
    }

    fun validatePasswordAfterInputChange(binding: FragmentLoginBinding) {
        binding.passwordInput.doAfterTextChanged { validatePassword(binding) }
    }

    fun validatePassword(binding: FragmentLoginBinding): Boolean {
        return binding.passwordInput.validator()
            .nonEmpty()
            .minLength(5)
            .atleastOneNumber()
            .addErrorCallback {
                binding.passwordInput.error = it
            }.check()
    }
}