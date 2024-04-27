package com.example.brainbooster.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.brainbooster.R
import com.example.brainbooster.databinding.FragmentProfileBinding



private lateinit var binding: FragmentProfileBinding

class ProfileFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater,container,false)
        val view = binding.root

        binding.menu.setItemSelected(R.id.profile)
        binding.menu.setOnItemSelectedListener {
            if (it==R.id.home)
                findNavController().navigate(R.id.action_profileFragment_to_mainMenuFragment)
            if (it==R.id.leaderboard)
                findNavController().navigate(R.id.action_profileFragment_to_leaderboardFragment)
        }







        return view
    }


}