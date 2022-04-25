package cz.muni.log4ts.ui.auth.register

import androidx.core.widget.doAfterTextChanged
import com.wajahatkarim3.easyvalidation.core.view_ktx.validator
import cz.muni.log4ts.databinding.FragmentRegisterBinding
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RegisterValidator @Inject constructor() {
    fun validateUsernameAfterInputChange(binding: FragmentRegisterBinding) {
        binding.usernameInput.doAfterTextChanged { validateUsername(binding) }
    }

    fun validateUsername(binding: FragmentRegisterBinding): Boolean {
         return binding.usernameInput.validator()
            .nonEmpty()
            .minLength(5)
            .addErrorCallback {
                binding.usernameInput.error = it
            }.check()
    }

    fun validateEmailAfterInputChange(binding: FragmentRegisterBinding) {
        binding.emailInput.doAfterTextChanged { validateEmail(binding) }
    }

    fun validateEmail(binding: FragmentRegisterBinding): Boolean {
        return binding.emailInput.validator()
            .nonEmpty()
            .validEmail()
            .addErrorCallback {
                binding.emailInput.error = it
            }.check()
    }

    fun validatePasswordAfterInputChange(binding: FragmentRegisterBinding) {
        binding.passwordInput.doAfterTextChanged { validatePassword(binding) }
    }

    fun validatePassword(binding: FragmentRegisterBinding): Boolean {
        return binding.passwordInput.validator()
            .nonEmpty()
            .minLength(5)
            .atleastOneNumber()
            .addErrorCallback {
                binding.passwordInput.error = it
            }.check()
    }
}