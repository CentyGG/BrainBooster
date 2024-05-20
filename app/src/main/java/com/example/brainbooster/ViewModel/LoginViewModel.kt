package com.example.brainbooster.ViewModel

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.brainbooster.Activity.MainActivity
import com.example.brainbooster.Controller.LoginController
import com.example.brainbooster.Domain.UserModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

private lateinit var auth: FirebaseAuth
private lateinit var sharedPreferences: SharedPreferences
class LoginViewModel: ViewModel() {
    private val email_ = MutableLiveData<String>()
    private val password_ = MutableLiveData<String>()
    private val loginController = LoginController()
    private val loginStatus :MutableStateFlow<Boolean?> = MutableStateFlow(null)
    var status = loginStatus.asStateFlow()
    init {
        auth = Firebase.auth
    }
    fun getStatus():Boolean?{
        return status.value
    }
    fun setEmail(username: String) {
        this.email_.value = username
    }

    fun setPassword(password: String) {
        this.password_.value = password
    }
    fun getEmail():String{
        return email_.value.toString()
    }
    fun getPassword():String{
        return password_.value.toString()
    }
    fun login(context: Context) {
        var email = getEmail()
        var password = getPassword()
        viewModelScope.launch {
            if (email != null && password != null) {
                var uid = loginController.loginUser(email, password)
                if (uid == "0") {
                    Toast.makeText(context, "Wrong email or wrong password", Toast.LENGTH_LONG)
                        .show()
                    loginStatus.value = false
                } else {
                    saveUserUid(context, uid)
                    Log.d(ContentValues.TAG, "SUCCEES")
                    loginStatus.value = true
                }
            } else {
                Toast.makeText(context, "Email or password is null.", Toast.LENGTH_LONG).show()
                loginStatus.value = false
            }
        }
    }
    private fun saveUserUid(context: Context, uid: String?) {
        sharedPreferences = context.getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
        sharedPreferences.edit().putString("UID", uid).apply()
    }
}