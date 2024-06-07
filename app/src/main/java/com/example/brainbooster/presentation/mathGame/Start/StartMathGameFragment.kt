package com.example.brainbooster.presentation.mathGame.Start

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.brainbooster.R
import com.example.brainbooster.databinding.FragmentStartMathGameBinding


class StartMathGameFragment : Fragment() {
private lateinit var binding: FragmentStartMathGameBinding
    lateinit var startViewModel: MathStartViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStartMathGameBinding.inflate(inflater,container,false)
        var view = binding.root
        startViewModel = ViewModelProvider(requireActivity())[MathStartViewModel::class.java]
        binding.startGame.setOnClickListener {
            startViewModel.start(this)
        }
        binding.info.setOnClickListener{
            startViewModel.info(requireContext())
        }
        binding.backToMenu.setOnClickListener{
            findNavController().navigate(R.id.action_startMathGameFragment2_to_mainMenuFragment)
        }

        return view
    }


}