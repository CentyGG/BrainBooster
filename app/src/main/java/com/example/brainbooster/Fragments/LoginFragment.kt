package com.example.brainbooster.Fragments

import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.brainbooster.R
import com.example.brainbooster.ViewModel.LoginViewModel
import com.example.brainbooster.ViewModel.MenuViewModel
import com.example.brainbooster.ViewModel.RegistrationViewModel
import com.example.brainbooster.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {
    private lateinit var loginViewModel: LoginViewModel
    private var _binding:FragmentLoginBinding?=null
    private val binding get() = _binding!!;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)//ггвп
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater,container,false)
        val view = binding.root;
        loginViewModel = ViewModelProvider(requireActivity())[LoginViewModel::class.java]
        val menuViewModel = ViewModelProvider(requireActivity())[MenuViewModel::class.java]
        binding.loginButton.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                if (p0 != null) {
                    if (p0 != null) {
                        loginViewModel.setEmail(binding.username.text.toString())
                        loginViewModel.setPassword(binding.password.text.toString())
                        loginViewModel.login(context!!)
                        if (loginViewModel.getStatus()==true)
                            p0.findNavController().navigate(R.id.action_loginFragment2_to_mainMenuFragment)
                    }
                }

            }
        })
        binding.registationTexview.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                if (p0 != null) {
                    if (p0 != null) {
                        p0.findNavController().navigate(R.id.action_loginFragment2_to_registrationFragment2)
                    }
                }

            }
        })


        return view;
    }


}