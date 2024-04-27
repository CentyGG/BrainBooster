package com.example.brainbooster.ViewModel

import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {
    val id = MutableLiveData<String>()

    fun authorization(){
        //check client id
        //if id == null -> registration or sign in

    }
    fun registration(){
        //get mail
        //get password
        //signal
    }
    fun sign_in(){

    }
}