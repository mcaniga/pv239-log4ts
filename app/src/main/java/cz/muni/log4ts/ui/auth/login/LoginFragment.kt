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
import kotlinx.coroutines.launch
import javax.inject.Inject

// TODO: detect if user is offline, if is then show offline indicator, and don't let him sign in
// TODO: switching between fragments (eg. in click on bottom bar icon, or back arrow) during incompleted CRUD with firebase causes app crash, catch the exception and act appropriately
class LoginFragment: Fragment() {
    @Inject
    lateinit var loginFragmentAction: LoginFragmentAction;
    @Inject
    lateinit var loginFragmentExtractor: LoginFragmentExtractor;

    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentLoginBinding.inflate(LayoutInflater.from(context), container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        appComponent.injectLoginFragmentDeps(this)

        binding.loginButton.setOnClickListener {
            val newUser: LoginUser = loginFragmentExtractor.extractLoginUser(binding)
            val navController = findNavController()
            viewLifecycleOwner.lifecycleScope.launch {
                loginFragmentAction.loginUser(navController, newUser, view)
            }
        }

        binding.loginSignupTextView.setOnClickListener {
            loginFragmentAction.navigateToRegisterFragment(findNavController(), view)
        }
    }
}