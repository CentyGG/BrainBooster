package com.example.brainbooster.presentation.registration

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.brainbooster.R
import com.example.brainbooster.databinding.FragmentRegistrationBinding
import kotlinx.coroutines.launch

class RegistrationFragment : Fragment() {
    private lateinit var registrationViewModel: RegistrationViewModel
    private lateinit var binding: FragmentRegistrationBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegistrationBinding.inflate(inflater, container, false)
        var view = binding.root
        registrationViewModel = ViewModelProvider(requireActivity())[RegistrationViewModel::class.java]

        binding.loginButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                if (p0 != null) {
                    registrationViewModel.setEmail(binding.email.text.toString())
                    registrationViewModel.setNickName(binding.nickname.text.toString())
                    registrationViewModel.setPassword(binding.password.text.toString())
                    registrationViewModel.registerUser(context!!)
                    viewLifecycleOwner.lifecycleScope.launch {
                        viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                            registrationViewModel.status.collect {
                                Log.d(
                                    "qwerty",
                                    Navigation.findNavController(binding.root).currentDestination?.navigatorName.toString()
                                )
                                if (registrationViewModel.getStatus() == true)
                                    Navigation.findNavController(binding.root)
                                        .navigate(R.id.action_registrationFragment2_to_startFragment)
                            }
                        }
                    }
                }
            }
        })
        binding.loginTexview.setOnClickListener(object : View.OnClickListener {
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