package cz.muni.log4ts.ui.auth.register

import cz.muni.log4ts.data.entities.NewUser
import cz.muni.log4ts.databinding.FragmentRegisterBinding
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RegisterFragmentExtractor @Inject constructor() {
    fun extractNewUser(binding: FragmentRegisterBinding): NewUser {
        val username = binding.usernameInput.text.toString()
        val password = binding.passwordInput.text.toString()
        val email = binding.emailInput.text.toString()

        return NewUser(
            username = username,
            password = password,
            email = email,
        )
    }
}