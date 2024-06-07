package com.example.brainbooster.presentation.registration

import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.brainbooster.data.API.LoginAPI
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class RegistrationViewModel : ViewModel() {
    private val nickName = MutableLiveData<String>()
    private val email_ = MutableLiveData<String>()
    private val password_ = MutableLiveData<String>()
    private val loginAPI = LoginAPI()

    private lateinit var sharedPreferences: SharedPreferences
    private var registrationStatus : MutableStateFlow<Boolean?> = MutableStateFlow(null)
    var status = registrationStatus.asStateFlow()

    fun getStatus(): Boolean? {
        return status.value
    }
    fun setNickName(nickname: String) {
        this.nickName.value = nickname
    }

    fun setEmail(username: String) {
        this.email_.value = username
    }

    fun setPassword(password: String) {
        this.password_.value = password
    }
    fun getNickname():String{
        return nickName.value.toString()
    }
    fun getEmail():String{
        return email_.value.toString()
    }
    fun getPassword():String{
        return password_.value.toString()
    }
    fun registerUser(context: Context) {
        val email = getEmail()
        val password = getPassword()
        viewModelScope.launch {
            if (email != null && password != null) {
                var uid = loginAPI.registerUser(email, password)
                if (uid == "0") {
                    Toast.makeText(context, "Registration failed", Toast.LENGTH_LONG).show()
                    registrationStatus.value = false
                } else if (uid == "1") {
                    Toast.makeText(context, "Wrong email or easy password", Toast.LENGTH_LONG)
                        .show()
                    registrationStatus.value = false
                } else {
                    registrationStatus.value = true
                    saveUserUid(context, uid)
                    createUser(context, uid)
                }
            } else {
                Toast.makeText(context, "Email or password is null.", Toast.LENGTH_LONG).show()
                registrationStatus.value = false
            }
        }
    }
    fun createUser(context: Context, uid: String) {
        viewModelScope.launch{
            if (loginAPI.createUser(uid, getNickname()) == 0) {
                Toast.makeText(context, "Connection lost.", Toast.LENGTH_LONG).show()
            }
        }
    }
    private fun saveUserUid(context: Context, uid: String?) {
        sharedPreferences = context.getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
        sharedPreferences.edit().putString("UID", uid).apply()
    }
}