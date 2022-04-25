package cz.muni.log4ts.ui.auth.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import cz.muni.log4ts.Log4TSApplication.Companion.appComponent
import cz.muni.log4ts.data.entities.NewUser
import cz.muni.log4ts.databinding.FragmentRegisterBinding
import cz.muni.log4ts.ui.OfflineUIHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

class RegisterFragment : Fragment() {
    @Inject
    lateinit var registerFragmentAction: RegisterFragmentAction

    @Inject
    lateinit var registerFragmentExtractor: RegisterFragmentExtractor

    @Inject
    lateinit var offlineUIHandler: OfflineUIHandler

    @Inject
    lateinit var registerValidator: RegisterValidator

    private lateinit var binding: FragmentRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(LayoutInflater.from(context), container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        injectDependencies()
        showOfflineSnackbarIfOffline(view)
        enableRegisterButtonIfOnline()
        registerUserOnRegisterButtonClick(view)
        navigateToLoginOnLoginTextClick(view)
    }

    private fun showOfflineSnackbarIfOffline(view: View) {
        offlineUIHandler.showOfflineSnackbarIfOffline(requireContext(), view)
    }

    private fun navigateToLoginOnLoginTextClick(view: View) {
        binding.registerLoginTextView.setOnClickListener {
            registerFragmentAction.navigateToLogin(findNavController(), view)
        }
    }

    private fun registerUserOnRegisterButtonClick(view: View) {
        validateRegisterInputAfterInputChange()
        binding.registerButton.setOnClickListener {
            if (isRegisterInputValid()) {
                val newUser: NewUser = registerFragmentExtractor.extractNewUser(binding)
                viewLifecycleOwner.lifecycleScope.launch {
                    registerFragmentAction.registerUser(findNavController(), newUser, view)
                }
            }
        }
    }

    private fun validateRegisterInputAfterInputChange() {
        registerValidator.validateUsernameAfterInputChange(binding)
        registerValidator.validateEmailAfterInputChange(binding)
        registerValidator.validatePasswordAfterInputChange(binding)
    }

    private fun isRegisterInputValid(): Boolean {
        return registerValidator.validateUsername(binding) &&
                registerValidator.validateEmail(binding) &&
                registerValidator.validatePassword(binding)
    }

    private fun injectDependencies() {
        appComponent.injectRegisterFragmentDeps(this)
    }

    private fun enableRegisterButtonIfOnline() {
        offlineUIHandler.enableButtonIfOnline(requireContext(), binding.registerButton, "register")
    }
}