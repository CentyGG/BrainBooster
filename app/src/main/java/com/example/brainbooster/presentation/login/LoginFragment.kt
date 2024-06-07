package com.example.brainbooster.presentation.login

import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import com.example.brainbooster.R
import com.example.brainbooster.presentation.mainMenu.MenuViewModel
import com.example.brainbooster.databinding.FragmentLoginBinding
import kotlinx.coroutines.launch


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
        requireActivity().supportFragmentManager.popBackStack(this::class.java.simpleName, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        loginViewModel = ViewModelProvider(requireActivity())[LoginViewModel::class.java]
        val menuViewModel = ViewModelProvider(requireActivity())[MenuViewModel::class.java]
        binding.loginButton.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                if (p0 != null) {

                        loginViewModel.setEmail(binding.username.text.toString())
                        loginViewModel.setPassword(binding.password.text.toString())
                        loginViewModel.login(context!!)
                        viewLifecycleOwner.lifecycleScope.launch {
                            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                                loginViewModel.status.collect {
                                    if (loginViewModel.getStatus()==true)
                                        p0.findNavController().navigate(R.id.action_loginFragment2_to_mainMenuFragment)
                                }
                            }}
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

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                loginViewModel.toastFlow.collect {
                    Toast.makeText(requireContext(), it ?: "", Toast.LENGTH_LONG).show()
                }
            }
        }




        return view;
    }


}