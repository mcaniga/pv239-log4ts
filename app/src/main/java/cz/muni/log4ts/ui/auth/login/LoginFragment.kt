package cz.muni.log4ts.ui.auth.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import cz.muni.log4ts.Log4TSApplication.Companion.appComponent
import cz.muni.log4ts.data.entities.LoginUser
import cz.muni.log4ts.databinding.FragmentLoginBinding
import cz.muni.log4ts.ui.OfflineUIHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginFragment : Fragment() {
    @Inject
    lateinit var loginFragmentAction: LoginFragmentAction

    @Inject
    lateinit var loginFragmentExtractor: LoginFragmentExtractor

    @Inject
    lateinit var offlineUIHandler: OfflineUIHandler

    @Inject
    lateinit var loginValidator: LoginValidator

    private lateinit var binding: FragmentLoginBinding

    val TAG = LoginFragment::class.simpleName

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(LayoutInflater.from(context), container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        injectDependencies()
        showOfflineSnackbarIfOffline(view)
        enableLoginButtonIfOnline()
        loginUserOnLoginButtonClick(view)
        navigateToRegisterViewOnSignupTextClick(view)
        loginFragmentAction.loginUserIfSessionIsRunning(findNavController(), view)
    }

    private fun injectDependencies() {
        appComponent.injectLoginFragmentDeps(this)
    }

    private fun showOfflineSnackbarIfOffline(view: View) {
        offlineUIHandler.showOfflineSnackbarIfOffline(requireContext(), view)
    }

    private fun loginUserOnLoginButtonClick(view: View) {
        setLoginOnClickListenerOnLoginButton(view)
    }

    private fun navigateToRegisterViewOnSignupTextClick(view: View) {
        binding.loginSignupTextView.setOnClickListener {
            loginFragmentAction.navigateToRegisterFragment(findNavController(), view)
        }
    }

    private fun setLoginOnClickListenerOnLoginButton(view: View) {
        binding.loginButton.setOnClickListener {
            if (isLoginInputValid()) {
                val newUser: LoginUser = loginFragmentExtractor.extractLoginUser(binding)
                val navController = findNavController()
                viewLifecycleOwner.lifecycleScope.launch {
                    loginFragmentAction.loginUser(navController, newUser, view)
                }
            }
        }
    }

    private fun isLoginInputValid(): Boolean {
        val validationResults: ArrayList<Boolean> = ArrayList();
        validationResults.add(loginValidator.validateEmail(binding));
        validationResults.add(loginValidator.validatePassword(binding));
        return validationResults.all { it }
    }

    private fun enableLoginButtonIfOnline() {
        offlineUIHandler.enableButtonIfOnline(requireContext(), binding.loginButton, "login")
    }
}