package com.example.brainbooster.ViewModel

import android.app.AlertDialog
import android.content.Context
import androidx.lifecycle.ViewModel

class MathStartViewModel: ViewModel() {
    fun info(context: Context) {
        val alertDialogBuilder = AlertDialog.Builder(context)
        alertDialogBuilder.setTitle("Правила игры")
        alertDialogBuilder.setMessage("Вам по очереди будут показывать" +
                " последовательности плиток, по которым вам придется нажи" +
                "мать. Ваша задача запомнить последовательность и нажать на" +
                " плитки в нужном порядке. Постепенно длина последовательности" +
                " будет увеличиваться. Удачи!")
        alertDialogBuilder.setPositiveButton("OK") { dialog, which ->
            dialog.dismiss()
        }

        val dialog = alertDialogBuilder.create()
        dialog.show()
    }

}