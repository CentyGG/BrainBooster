package com.example.brainbooster.Fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import com.example.brainbooster.R
import com.example.brainbooster.ViewModel.MenuViewModel
import com.example.brainbooster.ViewModel.StartViewModel
import com.example.brainbooster.databinding.StartFragmentBinding


class StartFragment : Fragment() {

    lateinit var binding: StartFragmentBinding
    lateinit var startViewModel: StartViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = StartFragmentBinding.inflate(inflater,container,false)
        val view = binding.root

        Handler(Looper.getMainLooper()).postDelayed({
            startViewModel = ViewModelProvider(requireActivity())[StartViewModel::class.java]
            val menuViewModel = ViewModelProvider(requireActivity())[MenuViewModel::class.java]
            val uid: String? = startViewModel.isLogined(requireContext())
            if (!uid.isNullOrBlank()) {
                menuViewModel.setUid(uid!!)
                view.findNavController().navigate(R.id.action_startFragment_to_mainMenuFragment)
            } else {
                view.findNavController().navigate(R.id.action_startFragment_to_loginFragment2)
            }
        }, 6000)

        return view;
    }


}