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
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


private lateinit var auth: FirebaseAuth
class RegistrationViewModel : ViewModel() {
    private val nickName = MutableLiveData<String>()
    private val email_ = MutableLiveData<String>()
    private val password_ = MutableLiveData<String>()
    private lateinit var auth: FirebaseAuth
    private lateinit var sharedPreferences: SharedPreferences
    private val db = Firebase.firestore

    init {
        auth = Firebase.auth
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
    fun registerUser(context : Context) : Boolean {
        val email = getEmail()
        val password = getPassword()
        var permission = false
        if (email != null && password != null) {
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d(TAG, "createUserWithEmailAndPassword: success")
                        val user = auth.currentUser
                        val uid: String? = user?.uid
                        saveUserUid(context,uid)
                        createUser(uid)
                        Log.d(TAG, "User UID: $uid")
                        permission = true
                    } else {
                        Toast.makeText(context,"Wrong email or easy password",Toast.LENGTH_LONG).show()
                    }
                }
        } else {
            Toast.makeText(context,"Email or password is null.",Toast.LENGTH_LONG).show()
        }
        return  permission
    }
    private fun createUser(uid: String?) {
        val user = hashMapOf(
            "nickname" to getNickname(),
            "score_math" to 0,
            "score_memory" to 0,
            "imageid" to "person1"
        )
        uid?.let {
            db.collection("users")
                .document(it)
                .set(user)
                .addOnSuccessListener {
                    Log.d("RegistrationViewModel", "User document created in Firestore with ID: $it")
                }
                .addOnFailureListener { e ->
                    Log.e("RegistrationViewModel", "Error creating user document in Firestore", e)
                }
        }
    }
    private fun saveUserUid(context: Context, uid: String?) {
        sharedPreferences = context.getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
        sharedPreferences.edit().putString("UID", uid).apply()
    }
}
















