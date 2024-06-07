package com.example.brainbooster.presentation.memoryGame.Start

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.brainbooster.R
import com.example.brainbooster.databinding.FragmentStartMemoryGameBinding


class StartMemoryGameFragment : Fragment() {
    private lateinit var binding: FragmentStartMemoryGameBinding
    lateinit var startViewModel: MemoryStartViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStartMemoryGameBinding.inflate(inflater,container,false)
        var view = binding.root
        startViewModel = ViewModelProvider(requireActivity())[MemoryStartViewModel::class.java]
        binding.startGame.setOnClickListener {
            startViewModel.start(this)
        }
        binding.info.setOnClickListener{
            startViewModel.info(requireContext())
        }
        binding.backToMenu.setOnClickListener{
            findNavController().navigate(R.id.action_startMemoryGameFragment2_to_mainMenuFragment)
        }




        return view
    }


}