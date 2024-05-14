package com.example.brainbooster.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.brainbooster.R
import com.example.brainbooster.databinding.FragmentMathGameBinding


class MathGameFragment : Fragment() {
    private lateinit var binding: FragmentMathGameBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMathGameBinding.inflate(inflater,container,false)
        var view = binding.root




        
        return view
    }


}