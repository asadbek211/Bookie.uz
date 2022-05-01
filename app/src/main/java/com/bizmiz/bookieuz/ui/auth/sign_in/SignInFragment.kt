package com.bizmiz.bookieuz.ui.auth.sign_in

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.bizmiz.bookieuz.R
import com.bizmiz.bookieuz.databinding.FragmentSignInBinding
import com.bizmiz.bookieuz.ui.model.SignInData
import com.bizmiz.bookieuz.utils.ResourceState
import com.bizmiz.bookieuz.utils.SessionManager
import com.bizmiz.bookieuz.utils.showSoftKeyboard
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignInFragment : Fragment() {
    private lateinit var binding: FragmentSignInBinding
    private val signInViewModel: SignInViewModel by viewModel()
    private lateinit var sessionManager: SessionManager
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignInBinding.bind(
            inflater.inflate(
                R.layout.fragment_sign_in,
                container,
                false
            )
        )
        binding.txtSignUp.setOnClickListener {
            val navController = Navigation.findNavController(requireActivity(), R.id.main_container)
            navController.navigate(R.id.action_signInFragment_to_signUpFragment)
        }
        signInObserve()
        binding.btnSignIn.setOnClickListener {
            if (validate(
                    binding.loginNumberEt.text.toString().trim(),
                    binding.loginPasswordEt.text.toString().trim()
                )
            ) {
                signInViewModel.getSignIn(
                    SignInData(
                        binding.loginNumberEt.text.toString().trim().toLong(),
                        binding.loginPasswordEt.text.toString().trim().toLong()
                    )
                )
            }
        }
        binding.loginNumberEt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                if (binding.loginNumberEt.text.toString().trim().isNotEmpty()) {
                    binding.loginNumber.isErrorEnabled = false
                } else {
                    binding.loginNumber.isErrorEnabled = true
                    binding.loginNumber.error = "Nomer kirgiziwińiz kerek"
                }
            }

        })
        binding.loginPasswordEt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                if (binding.loginPasswordEt.text.toString().trim().isEmpty()) {
                    binding.loginPassword.isErrorEnabled = true
                    binding.loginPassword.error = "Parol kirgiziwińiz kerek"
                } else if (binding.loginPasswordEt.text.toString().trim().length < 8) {
                    binding.loginPassword.isErrorEnabled = true
                    binding.loginPassword.error = "Parol 8 nomerden kem bolmawi kerek"

                } else {
                    binding.loginPassword.isErrorEnabled = false
                }
            }

        })
        return binding.root
    }

    private fun signInObserve() {
        signInViewModel.signInData.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                ResourceState.SUCCESS -> {
                    if (it.data?.data != null) {
                        sessionManager = SessionManager(requireContext())
                        sessionManager.saveAuthToken(it.data.data.token, it.data.data.user_id)
                        val navController =
                            Navigation.findNavController(requireActivity(), R.id.main_container)
                        navController.navigate(R.id.action_signInFragment_to_drawer)
                    }

                }
                ResourceState.ERROR -> {
                    Toast.makeText(requireActivity(), it.message.toString(), Toast.LENGTH_SHORT)
                        .show()
                }
            }
        })
    }

    private fun validate(
        phone: String,
        password: String
    ): Boolean {
        return when {
            phone.isEmpty() -> {
                binding.loginNumber.error = "Nomer kirgiziwińiz kerek"
                binding.loginNumberEt.showSoftKeyboard()
                false
            }
            password.isEmpty() -> {
                binding.loginPassword.error = "Parol kirgiziwińiz kerek"
                binding.loginPasswordEt.showSoftKeyboard()
                false
            }
            password.length < 8 -> {
                binding.loginPassword.error = "Parol 8 nomerden kem bolmawi kerek"
                binding.loginPasswordEt.showSoftKeyboard()
                false
            }
            else -> true

        }
    }
}