package com.bizmiz.bookieuz.ui.auth.sign_in

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.navigation.Navigation
import com.bizmiz.bookieuz.R
import com.bizmiz.bookieuz.databinding.FragmentSignInBinding

class SignInFragment : Fragment() {
    private lateinit var binding: FragmentSignInBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
       binding = FragmentSignInBinding.bind(inflater.inflate(R.layout.fragment_sign_in, container, false))
        binding.txtSignUp.setOnClickListener {
            val navController = Navigation.findNavController(requireActivity(), R.id.main_container)
            navController.navigate(R.id.action_signInFragment_to_signUpFragment)
        }
        binding.btnSignIn.setOnClickListener {
            val navController = Navigation.findNavController(requireActivity(), R.id.main_container)
            navController.navigate(R.id.action_signInFragment_to_drawer)
        }
        return binding.root
    }
}