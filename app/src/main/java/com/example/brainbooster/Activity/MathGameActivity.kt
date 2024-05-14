package com.example.brainbooster.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.brainbooster.databinding.ActivityMathGameBinding

class MathGameActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMathGameBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMathGameBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}