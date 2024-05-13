package com.example.brainbooster.Activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModelProvider
import com.example.brainbooster.ViewModel.LoginViewModel
import com.example.brainbooster.ViewModel.MenuViewModel
import com.example.brainbooster.databinding.ActivityMainBinding
import com.google.firebase.FirebaseApp

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var username: EditText
    lateinit var password: EditText
    lateinit var loginButton: Button
    lateinit var menuViewModel: MenuViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        FirebaseApp.initializeApp(this)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        menuViewModel = ViewModelProvider(this)[MenuViewModel::class.java]
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE_GAME_FRAGMENT && resultCode == Activity.RESULT_OK) {
            val score = data?.getIntExtra("score", 0)
            if (score != null) {
                menuViewModel.setScoreMemory(score)
            }
        }
    }
    companion object {
        const val REQUEST_CODE_GAME_FRAGMENT = 1
    }
}