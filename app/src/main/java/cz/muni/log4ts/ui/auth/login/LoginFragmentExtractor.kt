package cz.muni.log4ts.ui.auth.login

import cz.muni.log4ts.data.entities.LoginUser
import cz.muni.log4ts.data.entities.NewUser
import cz.muni.log4ts.databinding.FragmentLoginBinding
import cz.muni.log4ts.databinding.FragmentRegisterBinding
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginFragmentExtractor @Inject constructor() {
    fun extractLoginUser(binding: FragmentLoginBinding): LoginUser {
        val password = binding.passwordInput.text.toString()
        val email = binding.emailInput.text.toString()

        return LoginUser(
            password = password,
            email = email,
        )
    }
}