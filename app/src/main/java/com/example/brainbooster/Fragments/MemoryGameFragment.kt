package com.example.brainbooster.Fragments

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.view.forEach
import androidx.lifecycle.lifecycleScope
import com.example.brainbooster.Activity.MainActivity
import com.example.brainbooster.R
import com.example.brainbooster.databinding.FragmentMemoryGameBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class MemoryGameFragment : Fragment(),View.OnClickListener {
    private lateinit var binding: FragmentMemoryGameBinding
    private var score = 0
    private var result : String = ""
    private var userAnswer : String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMemoryGameBinding.inflate(inflater,container,false)
        var view = binding.root
        binding.apply {
            panel1.setOnClickListener(this@MemoryGameFragment)
            panel2.setOnClickListener(this@MemoryGameFragment)
            panel3.setOnClickListener(this@MemoryGameFragment)
            panel4.setOnClickListener(this@MemoryGameFragment)
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
            val round = (3 .. 5).random()
            repeat(round) {

                delay(400)
                val randomPanel = (1 .. 4).random()
                result += randomPanel

                val panel = when (randomPanel)
                {
                    1 -> binding.panel1
                    2 -> binding.panel2
                    3 -> binding.panel3
                    else -> binding.panel4
                }

                val drawableYellow =
                    ActivityCompat.getDrawable(this@MemoryGameFragment.requireContext() , R.drawable.btn_yellow)
                val drawableDefault =
                    ActivityCompat.getDrawable(this@MemoryGameFragment.requireContext() , R.drawable.btn_state)
                panel.background = drawableYellow
                delay(1000)
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
                else -> ""
            }

            if (userAnswer == result) {

                Toast.makeText(this@MemoryGameFragment.requireContext(), "W I N :)", Toast.LENGTH_SHORT).show()
                score+=100
                binding.tvScore.text = score.toString()
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
                loseGame(score)
            }
        }
    }

    private fun loseGame(score: Int) {
        val alertDialogBuilder = AlertDialog.Builder(requireContext())
        alertDialogBuilder.setTitle("Игра окончена!")
        alertDialogBuilder.setMessage("Вы набрали: $score")
        alertDialogBuilder.setPositiveButton("Заново") { dialog, which ->
            dialog.dismiss()
            startGame()
        }
        alertDialogBuilder.setNegativeButton("Меню") { dialog, which ->
            val intent = Intent()
            intent.putExtra("score", score)
            activity?.setResult(Activity.RESULT_OK, intent)
            activity?.finish()
            dialog.dismiss()
        }

        val dialog = alertDialogBuilder.create()
        dialog.show()
    }

}