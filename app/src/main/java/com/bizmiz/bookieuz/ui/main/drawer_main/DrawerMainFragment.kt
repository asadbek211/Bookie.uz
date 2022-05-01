package com.bizmiz.bookieuz.ui.main.drawer_main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.Navigation
import com.bizmiz.bookieuz.R
import com.bizmiz.bookieuz.databinding.FragmentDrawerMainBinding
import com.bizmiz.bookieuz.ui.main.MainFragment
import com.bizmiz.bookieuz.utils.SessionManager

class DrawerMainFragment : Fragment() {
    private var isDrawerClick:Boolean = false
    private lateinit var sessionManager: SessionManager
    private lateinit var binding: FragmentDrawerMainBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
       binding = FragmentDrawerMainBinding.bind(inflater.inflate(R.layout.fragment_drawer_main, container, false))
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.container, MainFragment()).commit()
        binding.drawerBtn.setOnClickListener {
            if (isDrawerClick){
                binding.containerVisible.visibility = View.GONE
                binding.drawerBtn.setImageResource(R.drawable.ic_menu)
                binding.drawerContainer.animate().translationX(-800f).duration = 500
                isDrawerClick = false
            }else{
                binding.containerVisible.visibility = View.VISIBLE
                binding.drawerBtn.setImageResource(R.drawable.ic_menu_black)
                binding.drawerContainer.animate().translationX(800f).duration = 500
                isDrawerClick = true
            }
            binding.containerVisible.setOnClickListener {
                if (isDrawerClick){
                    binding.containerVisible.visibility = View.GONE
                    binding.drawerBtn.setImageResource(R.drawable.ic_menu)
                    binding.drawerContainer.animate().translationX(-800f).duration = 500
                    isDrawerClick = false
                }
            }
        }
        binding.txtLogOut.setOnClickListener {
            sessionManager = SessionManager(requireContext())
            sessionManager.removeToken()
            val navController = Navigation.findNavController(requireActivity(), R.id.main_container)
            navController.navigate(R.id.action_drawer_to_signUpFragment)
        }
//        binding.txtTheme.setOnClickListener {
//            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
//        }
        binding.drawerContainer.setOnClickListener {}
        return binding.root
    }
}