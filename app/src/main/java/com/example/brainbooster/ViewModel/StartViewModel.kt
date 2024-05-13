package com.example.brainbooster.ViewModel

import android.content.Context
import androidx.lifecycle.ViewModel

class StartViewModel: ViewModel() {
    fun isLogined(context: Context): String? {
        val sharedPreferences = context.getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
        val uid = sharedPreferences.getString("UID", null)
        return if (!uid.isNullOrBlank()) {
            uid
        } else {
            null
        }
    }
}