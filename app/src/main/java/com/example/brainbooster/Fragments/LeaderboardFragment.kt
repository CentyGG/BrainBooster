package com.example.brainbooster.Fragments

import com.example.brainbooster.Adapter.LeaderAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.brainbooster.Domain.UserModel
import com.example.brainbooster.R
import com.example.brainbooster.databinding.FragmentLeaderboardBinding


private lateinit var binding: FragmentLeaderboardBinding

class LeaderboardFragment : Fragment() {
    private val leaderAdapter by lazy { LeaderAdapter() }




    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentLeaderboardBinding.inflate(inflater,container,false)
        val view = binding.root

        binding?.apply {
            scoreTxt1.text=loadData().get(0).score.toString()
            scoreTxt2.text=loadData().get(1).score.toString()
            scoreTxt3.text= loadData()?.get(2)?.score.toString()
            titleTop1.text=loadData().get(0).name
            titleTop2.text=loadData().get(1).name
            titleTop3.text=loadData().get(2).name

            var drawableResourceId1: Int = binding.root.resources.getIdentifier(
                loadData().get(0).pic,"drawable",binding.root.context.packageName
            )
            var drawableResourceId2: Int = binding.root.resources.getIdentifier(
                loadData().get(1).pic,"drawable",binding.root.context.packageName
            )
            var drawableResourceId3: Int = binding.root.resources.getIdentifier(
                loadData().get(2).pic,"drawable",binding.root.context.packageName
            )
            Glide.with(root.context)
                .load(drawableResourceId1).into(imageViewTop1)
            Glide.with(root.context)
                .load(drawableResourceId2).into(imageViewTop2)
            Glide.with(root.context)
                .load(drawableResourceId3).into(imageViewTop3)
            menu.setItemSelected(R.id.leaderboard)
            menu.setOnItemSelectedListener {
                if (it== R.id.home)
                {
                    findNavController().navigate(R.id.action_leaderboardFragment_to_mainMenuFragment)
                }
                if (it==R.id.profile)
                {
                    findNavController().navigate(R.id.action_leaderboardFragment_to_profileFragment)
                }

            }
        }
        var list:MutableList<UserModel> = loadData()
        list.removeAt(0)
        list.removeAt(1)
        list.removeAt(2)
        leaderAdapter.differ.submitList(list)
        binding.leaderView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = leaderAdapter
        }






       return view
    }
    private  fun loadData():MutableList<UserModel>{
        val users: MutableList<UserModel> = mutableListOf()
        users.add((UserModel(0,"Makar","person1",3000)))
        users.add((UserModel(1,"Vasya","person2",2000)))
        users.add((UserModel(2,"Makar","person1",1000)))
        users.add((UserModel(3,"Makar","person1",4000)))
        users.add((UserModel(4,"Makar","person1",4000)))
        users.add((UserModel(5,"Makar","person1",2000)))
        users.add((UserModel(6,"Makar","person1",44000)))
        users.add((UserModel(7,"Makar","person1",4000)))
        users.add((UserModel(8,"Makar","person1",4000)))
        return users
    }
}