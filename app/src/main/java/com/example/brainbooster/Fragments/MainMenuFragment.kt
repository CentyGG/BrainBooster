package com.example.brainbooster.Fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.brainbooster.Activity.MemoryGameActivity
import com.example.brainbooster.R
import com.example.brainbooster.ViewModel.MenuViewModel
import com.example.brainbooster.databinding.FragmentMainMenuBinding


private lateinit var binding: FragmentMainMenuBinding

class MainMenuFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainMenuBinding.inflate(inflater,container,false)
        val view = binding.root
        val menuViewModel = ViewModelProvider(requireActivity())[MenuViewModel::class.java]
        menuViewModel.getPerson(menuViewModel.getUid()!!)
        binding.menu.setItemSelected(R.id.home)
        binding.menu.setOnItemSelectedListener {
            if (it == R.id.leaderboard) {
                findNavController().navigate(R.id.action_mainMenuFragment_to_leaderboardFragment);
            }
            if (it == R.id.profile) {
                findNavController().navigate(R.id.action_mainMenuFragment_to_profileFragment);
            }
        }
        binding.nickname.text = menuViewModel.getNickname()

        binding.memoryGameB.setOnClickListener {
            val intent = Intent(requireContext(), MemoryGameActivity::class.java)
            startActivity(intent)
        }


            return view
    }

}