package com.example.brainbooster.presentation.leaderboard

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.brainbooster.data.API.UserAPI
import com.example.brainbooster.domain.entity.UserModel
import kotlinx.coroutines.launch

class LeaderBoardViewModel : ViewModel() {
    val _list: MutableLiveData<MutableList<UserModel>> = MutableLiveData()
    private var main_list :MutableList<UserModel>? = mutableListOf()
    var userAPI = UserAPI()

    fun loadData(){
        var list:MutableList<UserModel> = mutableListOf()
        viewModelScope.launch {
            userAPI.loadUsers(list)
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