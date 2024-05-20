package com.example.brainbooster.Fragments

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
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
        startViewModel = ViewModelProvider(requireActivity())[StartViewModel::class.java]
        if (startViewModel.isNetworkAvailable(requireContext())) {

            Handler(Looper.getMainLooper()).postDelayed({

                val menuViewModel = ViewModelProvider(requireActivity())[MenuViewModel::class.java]
                val uid: String? = startViewModel.isLogined(requireContext())
                if (!uid.isNullOrBlank()) {
                    menuViewModel.setUid(uid!!)
                    view.findNavController().navigate(R.id.action_startFragment_to_mainMenuFragment)
                } else {
                    view.findNavController().navigate(R.id.action_startFragment_to_loginFragment2)
                }
            }, 6000)
        }
        else
        {
            val builder = AlertDialog.Builder(requireContext())
            builder.setTitle("Ошибка сетевого подключения")
                .setMessage("Соединение с сервером потеряно, убедитесь, что вы подсоединены к интернету.")
                .setPositiveButton("Перейзайти") { _, _ ->
                    val intent = requireActivity().packageManager.getLaunchIntentForPackage(requireActivity().packageName)
                    intent?.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    intent?.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    requireActivity().startActivity(intent)
                    requireActivity().finish()
                }
                .setNegativeButton("Выйти") { dialog, _ ->
                    dialog.dismiss()
                    requireActivity().finish()
                }
                .show()
        }

        return view;
    }


}