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

// TODO: switching between fragments (eg. in click on bottom bar icon, or back arrow) during incompleted CRUD with firebase causes app crash, catch the exception and act appropriately
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
        validateLoginInputAfterInputChange()
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

    private fun validateLoginInputAfterInputChange() {
        loginValidator.validateEmailAfterInputChange(binding)
        loginValidator.validatePasswordAfterInputChange(binding)
    }

    private fun isLoginInputValid(): Boolean {
        return loginValidator.validateEmail(binding) && loginValidator.validatePassword(binding)
    }

    private fun enableLoginButtonIfOnline() {
        offlineUIHandler.enableButtonIfOnline(requireContext(), binding.loginButton, "login")
    }
}