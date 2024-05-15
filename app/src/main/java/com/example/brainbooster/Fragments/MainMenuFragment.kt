package com.example.brainbooster.Fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.brainbooster.Activity.MemoryGameActivity
import com.example.brainbooster.R
import com.example.brainbooster.ViewModel.MenuViewModel
import com.example.brainbooster.databinding.FragmentMainMenuBinding
import kotlinx.coroutines.launch


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
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                menuViewModel.user.collect {
                    binding.nickname.text = menuViewModel.user.value?.name ?: "Default Name"
                }

            }}

        binding.memoryGameB.setOnClickListener {
            val intent = Intent(requireContext(), MemoryGameActivity::class.java)
            startActivity(intent)
        }


        return view
    }

}