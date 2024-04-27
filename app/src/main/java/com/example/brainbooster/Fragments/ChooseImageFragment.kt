package com.example.brainbooster.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.brainbooster.Adapter.ImageAdapter
import com.example.brainbooster.R
import com.example.brainbooster.databinding.FragmentChooseImageBinding

private lateinit var binding: FragmentChooseImageBinding

class ChooseImageFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChooseImageBinding.inflate(inflater, container, false)
        val view = binding.root

        val imageAdapter = ImageAdapter(requireContext())

        binding.imageRecyclerView.adapter = imageAdapter
        binding.imageRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        return view
    }
}