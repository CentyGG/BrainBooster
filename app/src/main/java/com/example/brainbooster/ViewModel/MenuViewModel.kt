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

class MenuViewModel: ViewModel() {
    private var uid = MutableLiveData<String>()
    private var name = MutableLiveData<String>()
    private var image_id = MutableLiveData<String>()
    private var score1 = MutableLiveData<Int>()
    private var score2 = MutableLiveData<Int>()
    fun setUid(uid_:String){
        this.uid.value = uid_
    }

    fun getPerson(uid: String) {
        val db = Firebase.firestore
        val userRef = db.collection("users").document(uid)

        userRef.get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    val user = document.toObject(UserModel::class.java)
                    user?.let {
                        setNickname(it.name)
                        setImageId(it.image_id)
                        setScoreMath(it.score1)
                        setScoreMemory(it.score2)
                    }
                } else {

                }
            }
            .addOnFailureListener { exception ->

            }
    }

    fun getUid():String?{
        return uid.value
    }
    fun setNickname(nickname_:String){
        this.name.value = nickname_
    }
    fun getNickname():String{
        return name.value!!
    }
    fun setImageId(image_id_:String){
        this.image_id.value = image_id_
    }
    fun getImageId():String{
        return image_id.value!!
    }
    fun setScoreMath(score_math_:Int){
        this.score1.value = score_math_
    }
    fun getScoreMath():Int{
        return score1.value!!
    }
    fun setScoreMemory(score_memory_: Int){
        this.score2.value = score_memory_
    }
    fun getScoreMemory():Int{
        return score2.value!!
    }


}