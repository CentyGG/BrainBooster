package com.example.brainbooster.presentation.start

import android.animation.Animator.AnimatorListener
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.bumptech.glide.request.transition.ViewPropertyTransition.Animator
import com.example.brainbooster.R
import com.example.brainbooster.presentation.mainMenu.MenuViewModel
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
        val menuViewModel = ViewModelProvider(requireActivity())[MenuViewModel::class.java]
        var uid: String? = null
        if (startViewModel.isNetworkAvailable(requireContext()))
        {
            binding.animationView.addAnimatorListener(object :AnimatorListener{
                override fun onAnimationStart(p0: android.animation.Animator) {
                    uid = startViewModel.isLogined(requireContext())
                }

                override fun onAnimationEnd(p0: android.animation.Animator) {
                    if (!uid.isNullOrBlank()) {
                        menuViewModel.setUid(uid!!)
                        view.findNavController().navigate(R.id.action_startFragment_to_mainMenuFragment)
                    } else {
                        view.findNavController().navigate(R.id.action_startFragment_to_loginFragment2)
                    }
                }

                override fun onAnimationCancel(p0: android.animation.Animator) {
                    TODO("Not yet implemented")
                }

                override fun onAnimationRepeat(p0: android.animation.Animator) {
                    TODO("Not yet implemented")
                }


            })
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