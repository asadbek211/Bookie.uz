package com.bizmiz.bookieuz.ui.splash

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.bizmiz.bookieuz.R
import com.bizmiz.bookieuz.databinding.FragmentSplashBinding
import com.bizmiz.bookieuz.utils.SessionManager

class SplashFragment : Fragment() {
    private lateinit var binding: FragmentSplashBinding
    private lateinit var sessionManager: SessionManager
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        requireActivity().window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        Handler().postDelayed({
            sessionManager = SessionManager(requireContext())
//            if (sessionManager.fetchAuthToken() != null) {
                val navController =
                    Navigation.findNavController(requireActivity(), R.id.main_container)
                navController.navigate(R.id.action_splash_to_drawer)
//            } else {
//                val navController =
//                    Navigation.findNavController(requireActivity(), R.id.main_container)
//                navController.navigate(R.id.action_splash_to_signUpFragment)
//            }

        }, 2000)
        binding =
            FragmentSplashBinding.bind(inflater.inflate(R.layout.fragment_splash, container, false))
        return binding.root
    }
}