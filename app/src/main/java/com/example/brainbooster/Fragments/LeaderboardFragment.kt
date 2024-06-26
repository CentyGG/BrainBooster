package com.example.brainbooster.Fragments

import com.example.brainbooster.Adapter.LeaderAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.brainbooster.Adapter.MemoryLeaderAdapter
import com.example.brainbooster.Domain.UserModel
import com.example.brainbooster.R
import com.example.brainbooster.ViewModel.LeaderBoardViewModel
import com.example.brainbooster.ViewModel.MenuViewModel
import com.example.brainbooster.databinding.FragmentLeaderboardBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


private lateinit var binding: FragmentLeaderboardBinding

class LeaderboardFragment : Fragment() {
    private val leaderAdapter by lazy { LeaderAdapter() }
    private val memoryLeaderAdapter by lazy { MemoryLeaderAdapter() }
    private lateinit var list_from_firestore:MutableList<UserModel>





    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLeaderboardBinding.inflate(inflater,container,false)
        val view = binding.root
        val leaderBoardViewModel = ViewModelProvider(requireActivity())[LeaderBoardViewModel::class.java]
        leaderBoardViewModel.loadData()
        binding.menu.setItemSelected(R.id.leaderboard)
        binding.menu.setOnItemSelectedListener {
            if (it== R.id.home)
            {
                findNavController().navigate(R.id.action_leaderboardFragment_to_mainMenuFragment)
            }
            if (it==R.id.profile)
            {
                findNavController().navigate(R.id.action_leaderboardFragment_to_profileFragment)
            }

        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                leaderBoardViewModel.list.collect {
                    list_from_firestore = leaderBoardViewModel._list.value?: mutableListOf()
                    binding.buttonMath.setOnClickListener { mathLeaderboard(list_from_firestore) }
                    binding.buttonMemory.setOnClickListener {
                        memoryLeaderboard(
                            list_from_firestore
                        )
                    }
                    }
                }
            }
       return view
    }
    fun memoryLeaderboard(list_1:MutableList<UserModel>) {
        var list: MutableList<UserModel> =list_1.toMutableList().apply {
            sortByDescending { it.score_memory }
        }
        binding.scoreTxt1.text = list.get(0).score_memory.toString()
        binding.scoreTxt2.text = list.get(1).score_memory.toString()
        binding.scoreTxt3.text = list.get(2)?.score_memory.toString()
        binding.titleTop1.text = list.get(0).nickname
        binding.titleTop2.text = list.get(1).nickname
        binding.titleTop3.text = list.get(2).nickname

        var drawableResourceId1: Int = binding.root.resources.getIdentifier(
            list.get(0).imageid, "drawable", binding.root.context.packageName
        )
        var drawableResourceId2: Int = binding.root.resources.getIdentifier(
            list.get(1).imageid, "drawable", binding.root.context.packageName
        )
        var drawableResourceId3: Int = binding.root.resources.getIdentifier(
            list.get(2).imageid, "drawable", binding.root.context.packageName
        )
        Glide.with(binding.root.context)
            .load(drawableResourceId1).into(binding.imageViewTop1)
        Glide.with(binding.root.context)
            .load(drawableResourceId2).into(binding.imageViewTop2)
        Glide.with(binding.root.context)
            .load(drawableResourceId3).into(binding.imageViewTop3)

        list.removeAt(0)
        list.removeAt(1)
        list.removeAt(2)
        memoryLeaderAdapter.differ.submitList(list)
        binding.leaderView.adapter = memoryLeaderAdapter
    }
    fun mathLeaderboard(list_1:MutableList<UserModel>) {
        var list: MutableList<UserModel> = list_1.toMutableList().apply {
            sortByDescending { it.score_math }
        }
        binding.scoreTxt1.text = list.get(0).score_math.toString()
        binding.scoreTxt2.text = list.get(1).score_math.toString()
        binding.scoreTxt3.text = list?.get(2)?.score_math.toString()
        binding.titleTop1.text = list.get(0).nickname
        binding.titleTop2.text = list.get(1).nickname
        binding.titleTop3.text = list.get(2).nickname

        var drawableResourceId1: Int = binding.root.resources.getIdentifier(
            list.get(0).imageid, "drawable", binding.root.context.packageName
        )
        var drawableResourceId2: Int = binding.root.resources.getIdentifier(
            list.get(1).imageid, "drawable", binding.root.context.packageName
        )
        var drawableResourceId3: Int = binding.root.resources.getIdentifier(
            list.get(2).imageid, "drawable", binding.root.context.packageName
        )
        Glide.with(binding.root.context)
            .load(drawableResourceId1).into(binding.imageViewTop1)
        Glide.with(binding.root.context)
            .load(drawableResourceId2).into(binding.imageViewTop2)
        Glide.with(binding.root.context)
            .load(drawableResourceId3).into(binding.imageViewTop3)
        list.removeAt(0)
        list.removeAt(1)
        list.removeAt(2)
        leaderAdapter.differ.submitList(list)
        binding.leaderView.adapter = leaderAdapter

    }
}