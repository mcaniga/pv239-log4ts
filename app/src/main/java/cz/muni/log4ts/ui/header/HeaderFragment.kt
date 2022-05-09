package cz.muni.log4ts.ui.header

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import cz.muni.log4ts.Log4TSApplication.Companion.appComponent
import cz.muni.log4ts.databinding.FragmentHeaderBinding
import cz.muni.log4ts.repository.FirebaseUserDataRepository
import cz.muni.log4ts.ui.auth.signout.HeaderFragmentAction
import kotlinx.coroutines.launch
import javax.inject.Inject

class HeaderFragment: Fragment() {
    @Inject
    lateinit var headerFragmentAction: HeaderFragmentAction;

    @Inject
    lateinit var navDirectionsResolver: HeaderFragmentNavDirectionsResolver

    @Inject
    lateinit var firebaseUserDataRepository: FirebaseUserDataRepository;

    private lateinit var binding: FragmentHeaderBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentHeaderBinding.inflate(LayoutInflater.from(context), container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        appComponent.injectHeaderFragmentDeps(this)

        binding.namespaceTextview.text = "Log4TS"

        viewLifecycleOwner.lifecycleScope.launch {
            binding.usernameTextview.text = firebaseUserDataRepository.getCurrentUsername()
        }

        binding.logoutButton.setOnClickListener {
            val navController = findNavController()
            val signOutDirections = navDirectionsResolver.getSignOutDirections(this)
            headerFragmentAction.signOut(navController, view, signOutDirections)
        }
    }
}