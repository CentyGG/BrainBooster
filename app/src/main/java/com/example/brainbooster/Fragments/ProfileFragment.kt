package com.example.brainbooster.Fragments

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.brainbooster.R
import com.example.brainbooster.ViewModel.MenuViewModel
import com.example.brainbooster.databinding.FragmentProfileBinding
import kotlinx.coroutines.launch


private lateinit var binding: FragmentProfileBinding

class ProfileFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val menuViewModel = ViewModelProvider(requireActivity())[MenuViewModel::class.java]
        binding = FragmentProfileBinding.inflate(inflater,container,false)
        val view = binding.root
        binding.playerAvatar.setOnClickListener {
            val fm = childFragmentManager
            val dialog = ImageSelectionDialogFragment()
            dialog.show(fm, "image_selection_dialog")
            dialog.onImageSelectedListener = { imageId ->
                menuViewModel.setImageId("person"+imageId)
                menuViewModel.setUserImageId("person"+imageId)
                menuViewModel.sendData(requireContext())
                val str = menuViewModel.user.value?.imageid
                if (str != null) {
                    val drawableResourceId = resources.getIdentifier(str, "drawable", requireContext().packageName)
                    if (drawableResourceId != 0) {
                        binding.playerAvatar.setImageResource(drawableResourceId)
                        dialog.dismiss()
                    }
                }
            }
        }

        binding.menu.setItemSelected(R.id.profile)
        binding.menu.setOnItemSelectedListener {
            if (it==R.id.home)
                findNavController().navigate(R.id.action_profileFragment_to_mainMenuFragment)
            if (it==R.id.leaderboard)
                findNavController().navigate(R.id.action_profileFragment_to_leaderboardFragment)
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                menuViewModel.user.collect {
                    binding.nickname.text = menuViewModel.getNickname()
                    val str = menuViewModel?.user?.value?.imageid
                    Log.d(ContentValues.TAG, "WORKINGGGGGGGGGGGGG")
                    if (str != null) {
                        val drawableResourceId =
                            resources.getIdentifier(str, "drawable", requireContext().packageName)
                        if (drawableResourceId != 0) {
                            binding.playerAvatar.setImageResource(drawableResourceId)

                        }
                    }
                }
            }}
        return view
    }


}