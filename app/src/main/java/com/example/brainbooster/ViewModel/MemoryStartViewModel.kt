package com.example.brainbooster.ViewModel

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment
import com.example.brainbooster.Activity.MainActivity
import com.example.brainbooster.Activity.MemoryGameActivity
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.brainbooster.R

class MemoryStartViewModel() : ViewModel() {
    fun back(context: Context) {
        val intent = Intent(context, MainActivity::class.java,)
        context.startActivity(intent)
    }

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

    fun start(fragment: Fragment) {
        fragment.findNavController().navigate(R.id.action_startMemoryGameFragment_to_memoryGameFragment)
    }
}