package com.example.brainbooster.presentation.mainMenu

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.brainbooster.R
import com.example.brainbooster.databinding.FragmentMainMenuBinding
import kotlinx.coroutines.launch



class MainMenuFragment : Fragment() {

    private lateinit var binding: FragmentMainMenuBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainMenuBinding.inflate(inflater,container,false)
        val view = binding.root
        binding.shimmerLayout.startShimmer()
        val menuViewModel = ViewModelProvider(requireActivity())[MenuViewModel::class.java]
        menuViewModel.getPerson(requireContext(),menuViewModel.getUid(requireContext())!!)
        binding.menu.setItemSelected(R.id.home)
        binding.menu.setOnItemSelectedListener {
            if (it == R.id.leaderboard && (menuViewModel.user.value?.nickname!=null)) {
                findNavController().navigate(R.id.action_mainMenuFragment_to_leaderboardFragment);
            }
            if (it == R.id.profile &&(menuViewModel.user.value?.nickname!=null)) {
                findNavController().navigate(R.id.action_mainMenuFragment_to_profileFragment);
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                menuViewModel.user.collect {
                    if (menuViewModel.user.value?.nickname!=null) {
                        binding.nickname.text = menuViewModel.user.value?.nickname ?: "Default Name"
                        val str = menuViewModel.user.value?.imageid
                        if (str != null) {
                            val drawableResourceId =
                                resources.getIdentifier(
                                    str,
                                    "drawable",
                                    requireContext().packageName
                                )
                            if (drawableResourceId != 0) {
                                binding.playerAvatar.setImageResource(drawableResourceId)
                            }

                        }
                        binding.shimmerLayout.stopShimmer()
                        binding.shimmerLayout.visibility = View.GONE
                        binding.menuUser.visibility = View.VISIBLE
                    }

                }

            }}

        binding.memoryGameB.setOnClickListener {
            findNavController().navigate(R.id.action_mainMenuFragment_to_startMemoryGameFragment2)
        }
        binding.mathGame.setOnClickListener {
            findNavController().navigate(R.id.action_mainMenuFragment_to_startMathGameFragment2)
        }


        return view
    }

}