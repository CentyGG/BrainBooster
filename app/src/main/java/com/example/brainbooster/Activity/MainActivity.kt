package com.example.brainbooster.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModelProvider
import com.example.brainbooster.databinding.ActivityMainBinding
///сделать несколько таблиц(1 для онлайн, другая для офлайн)
///сдеать содержимое leaderboard фрагментом, а все виды таблиц хранить в другой
//сделать профиль
//выбор аватарки из предложенных
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var username: EditText
    lateinit var password: EditText
    lateinit var loginButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        Thread.sleep(2000)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()


        //val fragment: StartFragment = StartFragment()
        //supportFragmentManager.beginTransaction()
        //   .add(R.id.fragment_container_start, fragment)
        //   .commit()
    }
}