package com.example.brainbooster.ViewModel

import android.content.ContentValues.TAG
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

private lateinit var auth: FirebaseAuth
private lateinit var sharedPreferences: SharedPreferences
class LoginViewModel: ViewModel() {
    private val email_ = MutableLiveData<String>()
    private val password_ = MutableLiveData<String>()
    private val loginStatus = MutableLiveData<Boolean>()
    init {
        auth = Firebase.auth
    }
    fun getStatus():Boolean?{
        return loginStatus.value
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

        if (email != null && password != null) {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d(TAG, "signInWithEmail:success")
                        val user = auth.currentUser
                        val uid: String? = user?.uid
                        saveUserUid(context, uid)
                        loginStatus.value = true // Успешный вход
                    } else {
                        Toast.makeText(context, "Wrong email or wrong password", Toast.LENGTH_LONG).show()
                        loginStatus.value = false // Ошибка при входе
                    }
                }
        } else {
            Toast.makeText(context, "Email or password is null.", Toast.LENGTH_LONG).show()
            loginStatus.value = false // Ошибка при входе
        }
    }
    private fun saveUserUid(context: Context, uid: String?) {
        sharedPreferences = context.getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
        sharedPreferences.edit().putString("UID", uid).apply()
    }
}