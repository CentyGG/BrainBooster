package com.example.brainbooster.Fragments

import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.findNavController
import com.example.brainbooster.Activity.MainActivity
import com.example.brainbooster.R
import com.example.brainbooster.ViewModel.RegistrationViewModel
import com.example.brainbooster.databinding.FragmentRegistrationBinding
import com.google.firebase.FirebaseApp

private lateinit var binding: FragmentRegistrationBinding

class RegistrationFragment : Fragment() {
    private lateinit var registrationViewModel: RegistrationViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegistrationBinding.inflate(inflater, container, false)
        var view = binding.root
        registrationViewModel = ViewModelProvider(requireActivity())[RegistrationViewModel::class.java]

        binding.loginButton.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                if (p0 != null) {
                    if (p0 != null) {
                        registrationViewModel.setEmail(binding.email.text.toString())
                        registrationViewModel.setNickName(binding.nickname.text.toString())
                        registrationViewModel.setPassword(binding.password.text.toString())
                        if (registrationViewModel.registerUser(context!!))
                            p0.findNavController().navigate(R.id.action_registrationFragment2_to_mainMenuFragment)
                    }
                }
            }
        })
        binding.loginTexview.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                if (p0 != null) {
                    if (p0 != null) {
                        p0.findNavController().navigate(R.id.action_registrationFragment2_to_loginFragment2)
                    }
                }
            }
        })
        return view
    }


}