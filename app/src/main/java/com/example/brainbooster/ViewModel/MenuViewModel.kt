package com.example.brainbooster.ViewModel

import android.content.ContentValues.TAG
import android.nfc.Tag
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.brainbooster.Domain.UserModel
import com.google.firebase.Firebase
import com.google.firebase.firestore.auth.User
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MenuViewModel: ViewModel() {
    private var uid_: MutableStateFlow<String?> = MutableStateFlow(null)
    private var name = MutableLiveData<String>()
    private var imageid = MutableLiveData<String>()
    private var score_math = MutableLiveData<Int>()
    private var score_memory = MutableLiveData<Int>()
    private val _user: MutableStateFlow<UserModel?> = MutableStateFlow(null)
    val user = _user.asStateFlow()
    val uid = uid_.asStateFlow()
    fun setUid(uid_:String){
        this.uid_.value = uid_
    }

    fun getPerson(uid: String) {
        val db = Firebase.firestore
        val userRef = db.collection("users").document(uid)
        Log.d(TAG, "Attempting to retrieve data for uid: $uid")
        userRef.get()
            .addOnSuccessListener { document ->
                if (document!=null) {
                    Log.d(TAG, "Data retrieval successful")
                    val user = document.toObject(UserModel::class.java)
                    _user.value = user
                    Log.d(TAG, "nickname ${user?.name}")
                    Log.d(TAG, "score ${user?.score1}")
                    Log.d(TAG, "image id ${user?.image_id}")
                } else {
                    Log.d(TAG, "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.e(TAG, "Error getting user data", exception)
            }
    }

    fun getUid():String?{
        return uid_.value
    }
    fun setNickname(nickname_:String){
        this.name.value = nickname_
    }
    fun getNickname():String{
        return name.value!!
    }
    fun setImageId(image_id_:String){
        this.imageid.value = image_id_
    }
    fun getImageId():String{
        return imageid.value!!
    }
    fun setScoreMath(score_math_:Int){
        this.score_math.value = score_math_
    }
    fun getScoreMath():Int{
        return score_math.value!!
    }
    fun setScoreMemory(score_memory_: Int){
        this.score_memory.value = score_memory_
    }
    fun getScoreMemory():Int{
        return score_memory.value!!
    }


}