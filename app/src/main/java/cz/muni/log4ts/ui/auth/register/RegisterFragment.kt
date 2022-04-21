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
import kotlinx.coroutines.launch
import javax.inject.Inject

// TODO: detect if user is offline, if is then show offline indicator, and don't let him sign in
class RegisterFragment: Fragment() {
    @Inject
    lateinit var registerFragmentAction: RegisterFragmentAction;
    @Inject
    lateinit var registerFragmentExtractor: RegisterFragmentExtractor;

    private lateinit var binding: FragmentRegisterBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentRegisterBinding.inflate(LayoutInflater.from(context), container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        appComponent.injectRegisterFragmentDeps(this)

        binding.registerButton.setOnClickListener {
            val newUser: NewUser = registerFragmentExtractor.extractNewUser(binding)
            viewLifecycleOwner.lifecycleScope.launch {
                val navController = findNavController()
                registerFragmentAction.registerUser(navController, newUser, view)
            }
        }

        binding.registerLoginTextView.setOnClickListener {
            val navController = findNavController()
            navController.navigate(RegisterFragmentDirections.actionRegisterFragmentToLoginFragment())
        }
    }
}