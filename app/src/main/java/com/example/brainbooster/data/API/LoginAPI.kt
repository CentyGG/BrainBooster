package com.example.brainbooster.data.API

import android.content.ContentValues
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class LoginAPI {
    private val db = Firebase.firestore
    private var auth: FirebaseAuth
    init {
        auth = Firebase.auth
    }
    suspend fun createUser(uid: String?, nickname: String): Int = suspendCoroutine { continuation ->
        var answer: Int = 1
        val user = hashMapOf(
            "nickname" to nickname,
            "score_math" to 0,
            "score_memory" to 0,
            "imageid" to "person1"
        )
        Log.d("LoginController", "UID $uid")
        uid?.let {
            db.collection("users")
                .document(it)
                .set(user)
                .addOnSuccessListener {
                    Log.d("LoginController", "User document created in Firestore with ID: $it")
                    continuation.resume(1)
                }
                .addOnFailureListener { e ->
                    Log.e("LoginController", "Error creating user document in Firestore", e)
                    continuation.resume(0)
                }

        }
            ?: continuation.resume(0)
    }

    suspend fun registerUser(email: String, password: String): String {
        return suspendCoroutine { continuation ->
            var uid: String? = ""

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val user = auth.currentUser
                        uid = user?.uid
                        continuation.resume(uid ?: "0")
                    } else {
                        continuation.resume("1")
                    }
                }
        }
    }

    suspend fun loginUser(email: String, password: String): String =
        suspendCoroutine { continuation ->
            var uid: String
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d(ContentValues.TAG, "signInWithEmail:success")
                        val user = auth.currentUser
                        uid = user?.uid ?: "0"
                        continuation.resume(uid)
                    } else {
                        continuation.resume("0")
                    }
                }
        }
}