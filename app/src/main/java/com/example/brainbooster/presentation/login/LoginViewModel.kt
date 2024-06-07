package com.example.brainbooster.presentation.login

import android.content.ContentValues
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.brainbooster.data.API.LoginAPI
import com.example.brainbooster.domain.entity.UserUiState
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel: ViewModel() {
    private lateinit var auth: FirebaseAuth
    private lateinit var sharedPreferences: SharedPreferences
    private val email_ = MutableLiveData<String>()
    private val password_ = MutableLiveData<String>()
    private val loginAPI = LoginAPI()
    private val _userUiState = MutableStateFlow(UserUiState())
    val userUiState: StateFlow<UserUiState> = _userUiState
    private val _loginStatus : MutableStateFlow<Boolean?> = MutableStateFlow(null)
    private val _toastFlow = MutableSharedFlow<String?>()
    val toastFlow : SharedFlow<String?> = _toastFlow
    var status = _loginStatus.asStateFlow()
    init {
        auth = Firebase.auth
    }
    fun getStatus():Boolean?{
        return status.value
    }
    fun setEmail(username: String) {
        _userUiState.value = _userUiState.value.copy(
            login = username,
        )
    }

    fun setPassword(password: String) {
        _userUiState.value = _userUiState.value.copy(
            password = password
        )
    }
    fun getEmail():String{
        return email_.value.toString()
    }
    fun getPassword():String{
        return password_.value.toString()
    }
    fun login(context: Context) {
        var email = userUiState.value.login
        var password = userUiState.value.password
        viewModelScope.launch {
            if (email != null && password != null) {
                var uid = loginAPI.loginUser(email, password)
                if (uid == "0") {
                    Log.d("Login", "before emit")
                    _toastFlow.emit("Wrong email or wrong password")
                    _loginStatus.value = false
                } else {
                    saveUserUid(context, uid)
                    Log.d(ContentValues.TAG, "SUCCESS")
                    _loginStatus.value = true
                }
            } else {
                Toast.makeText(context, "Email or password is null.", Toast.LENGTH_LONG).show()
                _loginStatus.value = false
            }
        }
    }
    private fun saveUserUid(context: Context, uid: String?) {
        sharedPreferences = context.getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
        sharedPreferences.edit().putString("UID", uid).apply()
    }
}