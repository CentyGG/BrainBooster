package com.example.brainbooster.data.API

import android.content.ContentValues
import android.util.Log
import com.example.brainbooster.domain.entity.UserModel
import com.google.firebase.Firebase
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.tasks.await
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class UserAPI {
    private val db = Firebase.firestore
    suspend fun getPerson(uid: String): UserModel = suspendCoroutine{ continuation ->
        val userRef = db.collection("users").document(uid)
        var user_from_db : UserModel?= UserModel()
        Log.d(ContentValues.TAG, "Attempting to retrieve data for uid: $uid")
        userRef.get()
            .addOnSuccessListener { document ->
                if (document!=null) {
                    Log.d(ContentValues.TAG, "Data retrieval successful")
                    val user = document.toObject(UserModel::class.java)
                    Log.e(ContentValues.TAG, "USER FROM FIRESTORE $user")
                    user_from_db=user
                    continuation.resume(user_from_db ?: UserModel())
                } else {
                    Log.d(ContentValues.TAG, "No such document")
                    continuation.resume(UserModel())
                }
            }
            .addOnFailureListener { exception ->
                Log.e(ContentValues.TAG, "Error getting user data", exception)
                continuation.resume(UserModel())
            }
    }



    suspend fun sendData(name: String, score_math: Int, score_memory: Int, imageid: String, uid: String):Int {
        var result = 0
        coroutineScope {
            try {
                val userCollection = db.collection("users")
                val userDoc = userCollection.document(uid)
                val data = hashMapOf(
                    "nickname" to name,
                    "score_math" to score_math,
                    "score_memory" to score_memory,
                    "imageid" to imageid
                )
                userDoc.set(data, SetOptions.merge())
                    .await()
                Log.d(ContentValues.TAG, "Document successfully updated!")
                result =1

            } catch (e: Exception) {
                Log.e(ContentValues.TAG, "Error updating document", e)
                result=0
            }
        }
        return result
    }
    suspend fun loadUsers(list:MutableList<UserModel>):MutableList<UserModel> = suspendCoroutine { continuation ->
        val usersCollection = db.collection("users")
        usersCollection.get()
            .addOnSuccessListener { documents ->
                Log.d(ContentValues.TAG, "All documents installed")
                for (document in documents) {
                    val user = document.toObject(UserModel::class.java)
                    list.add(user)
                }
                continuation.resume(list)
            }
            .addOnFailureListener { exception ->
                Log.w(ContentValues.TAG, "Error getting documents: ", exception)
                continuation.resume(list)
            }
    }
}