package com.example.brainbooster.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MenuViewModel: ViewModel() {
    private var uid = MutableLiveData<String>()
    private var nickname = MutableLiveData<String>()
    private var image_id = MutableLiveData<String>()
    private var score_math = MutableLiveData<Int>()
    private var score_memory = MutableLiveData<Int>()
    fun setUid(uid_:String){
        this.uid.value = uid_
    }
    fun getUid():String{
        return uid.value!!
    }
    fun setNickname(nickname_:String){
        this.nickname.value = nickname_
    }
    fun getNickname():String{
        return nickname.value!!
    }
    fun setImageId(image_id_:String){
        this.image_id.value = image_id_
    }
    fun getImageId():String{
        return image_id.value!!
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