package com.example.brainbooster.Fragments

import android.app.Activity
import android.app.AlertDialog
import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.brainbooster.Activity.MainActivity
import com.example.brainbooster.Parser.ExpressionParser
import com.example.brainbooster.R
import com.example.brainbooster.ViewModel.MenuViewModel
import com.example.brainbooster.databinding.FragmentMathGameBinding


class MathGameFragment : Fragment(), View.OnClickListener {
    private lateinit var binding: FragmentMathGameBinding
    private var userAnswer = ""
    private var result = ""
    private var turn = 1
    private var score = 0
    private var timeLeftInMillis: Long = 10000
    private lateinit var timer: CountDownTimer
    private lateinit var menuViewModel: MenuViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMathGameBinding.inflate(inflater,container,false)
        var view = binding.root
        menuViewModel = ViewModelProvider(requireActivity())[MenuViewModel::class.java]
        timer = object : CountDownTimer(10000,999){
            override fun onTick(p0: Long) {
                timeLeftInMillis = p0
                binding.textViewCount.text = (p0/1000).toString()
            }

            override fun onFinish() {
                checkAnswer(userAnswer,0)

            }

        }
        binding.apply {
            one.setOnClickListener(this@MathGameFragment)
            two.setOnClickListener(this@MathGameFragment)
            three.setOnClickListener(this@MathGameFragment)
            four.setOnClickListener(this@MathGameFragment)
            five.setOnClickListener(this@MathGameFragment)
            six.setOnClickListener(this@MathGameFragment)
            seven.setOnClickListener(this@MathGameFragment)
            eight.setOnClickListener(this@MathGameFragment)
            nine.setOnClickListener(this@MathGameFragment)
            zero.setOnClickListener(this@MathGameFragment)
            equal.setOnClickListener(this@MathGameFragment)
            clear.setOnClickListener(this@MathGameFragment)
            backspace.setOnClickListener(this@MathGameFragment)
            minus.setOnClickListener(this@MathGameFragment)
            startGame()
        }
        return view
    }
    private fun generateMathExpression(): Int {
        val number1 = (1..14).random()
        val number2 = (1..17).random()
        val number3 = (1..9).random()

        val operations = listOf("+", "-", "*")
        val operation1 = operations.random()
        val operation2 = operations.random()

        val expression = "$number1 $operation1 $number2 $operation2 $number3"
        val result = evaluateExpression(expression)

        binding.formula.text = expression
        return result
    }
    private fun evaluateExpression(expression: String): Int {
        var result: Int
        try {
            val parser = ExpressionParser(expression)
            result = parser.evaluate()
        } catch (e: ArithmeticException) {
            result = 0 // Handle arithmetic exceptions if needed
        }
        return result
    }

    private fun startGame() {
        timer.start()
        userAnswer=""
        var exp = generateMathExpression().toString()
        result = exp
    }
    override fun onClick(view : View?) {
        view?.let {
             when (it.id) {
                R.id.one -> userAnswer+="1"
                R.id.two -> userAnswer+="2"
                R.id.three -> userAnswer+="3"
                R.id.four -> userAnswer+="4"
                R.id.five -> userAnswer+="5"
                R.id.six -> userAnswer+="6"
                R.id.seven -> userAnswer+="7"
                R.id.eight -> userAnswer+="8"
                R.id.nine -> userAnswer+="9"
                R.id.zero -> userAnswer+="0"
                R.id.clear -> userAnswer=""
                R.id.backspace -> userAnswer = userAnswer.substring(0,userAnswer.length-1)
                R.id.equal -> checkAnswer(userAnswer,1)
                R.id.minus -> userAnswer = "-" + userAnswer
            }
            binding.tvResult.text = userAnswer
        }
    }

    private fun checkAnswer(userAnswer: String,code_:Int) {

        if (userAnswer == result && code_==0) {
            score += 200
        }
        if (userAnswer == result && code_==1) {
            score += timeLeftInMillis.toInt()/10+150

        }
        Log.d(ContentValues.TAG, "answer ${userAnswer}")
        Log.d(ContentValues.TAG, "result  ${result}")
        Toast.makeText(this@MathGameFragment.requireContext(), "score $score", Toast.LENGTH_SHORT).show()


        if (turn!=10)
        {
            timer.cancel()
            binding.turnTable.text = (turn+1).toString() + "/10"
            turn+=1
            startGame()
        }
        else
        {
            timer.cancel()
            if (menuViewModel.getScoreMath()< score) {
                menuViewModel.setScoreMath(score)
                menuViewModel.sendData(requireContext())
            }
            val alertDialogBuilder = AlertDialog.Builder(requireContext())
            alertDialogBuilder.setTitle("Игра окончена!")
            alertDialogBuilder.setMessage("Вы набрали: $score")
            alertDialogBuilder.setPositiveButton("Заново") { dialog, which ->


                dialog.dismiss()
                score = 0
                turn =1
                binding.turnTable.text = (turn).toString() + "/10"
                startGame()
            }
            alertDialogBuilder.setNegativeButton("Меню") { dialog, which ->
                dialog.dismiss()
                findNavController().navigate(R.id.action_mathGameFragment2_to_mainMenuFragment)
            }

            val dialog = alertDialogBuilder.create()
            dialog.show()
        }
    }

}