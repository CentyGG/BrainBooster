package com.example.brainbooster.presentation.profile

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.brainbooster.domain.entity.UserUiState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel(){
    private val _clearFlow = MutableStateFlow<Boolean?>(null)
    val clearFlow : SharedFlow<Boolean?> = _clearFlow

    fun logout(context: Context){
        viewModelScope.launch {
            val sharedPreferences = context.getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
            sharedPreferences.edit().clear().apply()
            _clearFlow.value = true
        }
    }
}