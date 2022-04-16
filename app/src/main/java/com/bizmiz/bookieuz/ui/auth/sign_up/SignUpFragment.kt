package com.bizmiz.bookieuz.ui.auth.sign_up

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bizmiz.bookieuz.R
import com.bizmiz.bookieuz.databinding.FragmentSignUpBinding

class SignUpFragment : Fragment() {
    private lateinit var binding: FragmentSignUpBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
     binding = FragmentSignUpBinding.bind(inflater.inflate(R.layout.fragment_sign_up, container, false))
        return binding.root
    }
}