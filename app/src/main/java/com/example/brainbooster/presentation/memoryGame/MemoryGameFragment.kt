package com.example.brainbooster.presentation.memoryGame

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.app.ActivityCompat
import androidx.core.view.forEach
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.brainbooster.R
import com.example.brainbooster.presentation.mainMenu.MenuViewModel
import com.example.brainbooster.databinding.FragmentMemoryGameBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class MemoryGameFragment : Fragment(),View.OnClickListener {
    private lateinit var binding: FragmentMemoryGameBinding
    private var score: Int = 0
    private var result : String = ""
    private var userAnswer : String = ""
    private var difficulty =3
    private var popitka = 1
    private lateinit var menuViewModel: MenuViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMemoryGameBinding.inflate(inflater,container,false)
        var view = binding.root
        menuViewModel = ViewModelProvider(requireActivity())[MenuViewModel::class.java]
        binding.apply {
            panel1.setOnClickListener(this@MemoryGameFragment)
            panel2.setOnClickListener(this@MemoryGameFragment)
            panel3.setOnClickListener(this@MemoryGameFragment)
            panel4.setOnClickListener(this@MemoryGameFragment)
            panel5.setOnClickListener(this@MemoryGameFragment)
            panel6.setOnClickListener(this@MemoryGameFragment)
            panel7.setOnClickListener(this@MemoryGameFragment)
            panel8.setOnClickListener(this@MemoryGameFragment)
            panel9.setOnClickListener(this@MemoryGameFragment)
            startGame()
        }
        return view
    }
    private fun startGame()
    {
        result = ""
        userAnswer = ""
        disableButtons()
        lifecycleScope.launch {
            val round = difficulty
            repeat(round) {

                delay(450)
                val randomPanel = (1 .. 9).random()
                result += randomPanel

                val panel = when (randomPanel)
                {
                    1 -> binding.panel1
                    2 -> binding.panel2
                    3 -> binding.panel3
                    4 -> binding.panel4
                    5 -> binding.panel5
                    6 -> binding.panel6
                    7 -> binding.panel7
                    8 -> binding.panel8
                    else->binding.panel9
                }

                val drawableYellow =
                    ActivityCompat.getDrawable(this@MemoryGameFragment.requireContext() , R.drawable.btn_yellow)
                val drawableDefault =
                    ActivityCompat.getDrawable(this@MemoryGameFragment.requireContext() , R.drawable.btn_state)
                panel.background = drawableYellow
                delay(600)
                panel.background = drawableDefault

            }
            requireActivity().runOnUiThread {
                enableButtons()
            }
        }
    }
    private fun disableButtons()
    {
        binding.root.forEach { view ->
            if (view is Button)
            {
                view.isEnabled = false
            }
        }
    }
    private fun enableButtons()
    {
        binding.root.forEach { view ->
            if (view is Button)
            {
                view.isEnabled = true
            }
        }
    }
    override fun onClick(view : View?) {
        view?.let {
            userAnswer += when (it.id) {
                R.id.panel1 -> "1"
                R.id.panel2 -> "2"
                R.id.panel3 -> "3"
                R.id.panel4 -> "4"
                R.id.panel5 -> "5"
                R.id.panel6 -> "6"
                R.id.panel7 -> "7"
                R.id.panel8 -> "8"
                R.id.panel9 -> "9"
                else -> ""
            }

            if (userAnswer == result) {

                score+=100
                binding.tvScore.text = score.toString()
                popitka+=1
                if (popitka==4)
                {
                    difficulty+=1
                    popitka=1
                }
                startGame()

            } else if (userAnswer.length >= result.length) {
                loseAnimation()
            }
        }
    }
    private fun loseAnimation()
    {
        binding.apply {
            disableButtons()
            val drawableLose = ActivityCompat.getDrawable(this@MemoryGameFragment.requireContext() , R.drawable.btn_lose)
            val drawableDefault =
                ActivityCompat.getDrawable(this@MemoryGameFragment.requireContext() , R.drawable.btn_state)
            lifecycleScope.launch {
                binding.root.forEach { view ->
                    if (view is Button)
                    {
                        view.background = drawableLose
                        delay(200)
                        view.background = drawableDefault
                    }
                }
                delay(1000)
            }
        }
        loseGame()
    }

    private fun loseGame() {
        if (menuViewModel.getScoreMemory()< score) {
            menuViewModel.setScoreMemory(score)
            menuViewModel.sendData(requireContext())
        }
        val alertDialogBuilder = AlertDialog.Builder(requireContext())
        alertDialogBuilder.setTitle("Игра окончена!")
        alertDialogBuilder.setMessage("Вы набрали: $score")
        alertDialogBuilder.setPositiveButton("Заново") { dialog, which ->
            dialog.dismiss()
            score = 0
            difficulty=3
            popitka=1
            binding.tvScore.text=score.toString()
            startGame()
        }
        alertDialogBuilder.setNegativeButton("Меню") { dialog, which ->
            dialog.dismiss()
            findNavController().navigate(R.id.action_memoryGameFragment2_to_mainMenuFragment)
        }

        val dialog = alertDialogBuilder.create()
        dialog.show()
    }

}