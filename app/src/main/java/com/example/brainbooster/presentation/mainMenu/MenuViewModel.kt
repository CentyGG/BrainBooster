package com.example.brainbooster.presentation.mainMenu

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.brainbooster.data.API.UserAPI
import com.example.brainbooster.domain.entity.UserModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MenuViewModel: ViewModel() {
    private var uid_: MutableStateFlow<String?> = MutableStateFlow(null)
    private var name = MutableLiveData<String>()
    private var imageid = MutableLiveData<String>()
    private var score_math = MutableLiveData<Int>()
    private val userAPI = UserAPI()
    private var score_memory = MutableLiveData<Int>()
    private val _user: MutableStateFlow<UserModel?> = MutableStateFlow(null)
    val user = _user.asStateFlow()
    val uid = uid_.asStateFlow()
    fun setUid(uid_:String){
        this.uid_.value = uid_
        Log.d(TAG, "uid ${uid_}")
    }
    fun setUserImageId(image_id_: String){
        _user.value?.imageid = image_id_
        Log.d(TAG, "_USER changed")

    }

    fun getPerson(contex : Context,uid: String) {
        viewModelScope.launch {
            var new_user = userAPI.getPerson(uid)
            if (new_user != null) {
                _user.value = new_user
                setImageId(new_user!!.imageid)
                setNickname(new_user!!.nickname)
                setScoreMath(new_user!!.score_math)
                setScoreMemory(new_user!!.score_memory)
            } else {
                Toast.makeText(contex, "Connection lost.", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun getUid(context: Context):String?{
        if (uid.value==null) {
            val sharedPreferences = context.getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
            val uid = sharedPreferences.getString("UID", null)
            if (!uid.isNullOrBlank()) {
                setUid(uid)
            }
        }
            return uid_.value
    }
    fun setNickname(nickname_:String){
        this.name.value = nickname_
    }
    fun getNickname():String{
        return name.value!!
    }
    fun setImageId(image_id_:String){
        Log.d(ContentValues.TAG, "ID in viewModel ${image_id_}")
        Log.d(ContentValues.TAG, "user ${user.value}")

        this.imageid.value = image_id_
    }
    fun getImageId():String{
        return imageid.value!!
    }
    fun setScoreMath(score_math_:Int){
        this.score_math.value = score_math_
    }
    fun getScoreMath(): Int {
        return score_math.value ?: 0
    }
    fun setScoreMemory(score_memory_: Int){
        this.score_memory.value = score_memory_
    }
    fun getScoreMemory(): Int {
        return score_memory.value ?: 0
    }
    fun sendData(contex: Context) {
        viewModelScope.launch {
            var result = userAPI.sendData(name.value!!, score_math.value!!, score_memory.value!!,imageid.value!!,uid.value!!)
            if (result==0)
                Toast.makeText(contex, "Connection lost.", Toast.LENGTH_LONG).show()
        }
    }

}