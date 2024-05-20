package com.example.brainbooster.ViewModel

import android.content.ContentValues
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.brainbooster.Controller.UserController
import com.example.brainbooster.Domain.UserModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LeaderBoardViewModel : ViewModel() {
    val _list: MutableLiveData<MutableList<UserModel>> = MutableLiveData()
    private var main_list :MutableList<UserModel>? = mutableListOf()
    var userController =UserController()

    fun loadData(){
        var list:MutableList<UserModel> = mutableListOf()
        viewModelScope.launch {
            userController.loadUsers(list)
            if (list.isEmpty())
            {
                list.add((UserModel("person1","NO_CONNECTION",0,0)))
                list.add((UserModel("person1","NO_CONNECTION",0,0)))
                list.add((UserModel("person1","NO_CONNECTION",0,0)))
                _list.value=list
            }
            else
            {
                list.add((UserModel("person1","Anjela",1000,300)))
                list.add((UserModel("person2","Bob",2500,500)))
                list.add((UserModel("person3","Mike",6000,600)))
                list.add((UserModel("person4","Jason",3500,800)))
                list.add((UserModel("person5","Victoria",8000,1000)))
                _list.value=list
            }
        }
    }
}