package com.example.brainbooster.presentation.mathGame.Start

import android.app.AlertDialog
import android.content.Context
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.example.brainbooster.R

class MathStartViewModel: ViewModel() {
    fun info(context: Context) {
        val alertDialogBuilder = AlertDialog.Builder(context)
        alertDialogBuilder.setTitle("Правила игры")
        alertDialogBuilder.setMessage("Вам будут показаны выражения, " +
                "которые нужно будет решить. За каждый решенный пример вы получаете баллы. Чем " +
                "быстрее вы решите выражение, тем больше баллов вы получите. На каждое выражение вам выдается" +
                "по 10 секунд. Удачи!")
        alertDialogBuilder.setPositiveButton("OK") { dialog, which ->
            dialog.dismiss()
        }

        val dialog = alertDialogBuilder.create()
        dialog.show()
    }
    fun start(fragment: Fragment) {
        fragment.findNavController().navigate(R.id.action_startMathGameFragment2_to_mathGameFragment2)
    }

}