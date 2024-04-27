package com.example.brainbooster.Fragments

import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.brainbooster.R
import com.example.brainbooster.databinding.FragmentRegistrationBinding

private lateinit var binding: FragmentRegistrationBinding
class RegistrationFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegistrationBinding.inflate(inflater, container, false)
        var view = binding.root
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