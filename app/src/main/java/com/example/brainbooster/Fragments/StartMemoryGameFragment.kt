package com.example.brainbooster.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.brainbooster.R
import com.example.brainbooster.ViewModel.MemoryStartViewModel
import com.example.brainbooster.ViewModel.StartViewModel
import com.example.brainbooster.databinding.FragmentMemoryGameBinding
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
            startViewModel.info(context!!)
        }
        binding.backToMenu.setOnClickListener{
            requireActivity().finish()
        }




        return view
    }


}