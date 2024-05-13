package com.example.brainbooster.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.brainbooster.databinding.ActivityMemoryGameBinding
import com.example.brainbooster.databinding.FragmentRegistrationBinding

class MemoryGameActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMemoryGameBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMemoryGameBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}