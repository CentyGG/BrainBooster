package com.example.brainbooster.Fragments

import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.brainbooster.R
import com.example.brainbooster.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {

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
        binding.loginButton.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                if (p0 != null) {
                    if (p0 != null) {
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