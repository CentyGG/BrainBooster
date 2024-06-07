package com.example.brainbooster.presentation.leaderboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.brainbooster.domain.entity.UserModel

import com.example.brainbooster.R
import com.example.brainbooster.databinding.FragmentLeaderboardBinding

class LeaderboardFragment : Fragment() {
    private val leaderAdapter by lazy { LeaderAdapter() }
    private val memoryLeaderAdapter by lazy { MemoryLeaderAdapter() }
    private lateinit var list_from_firestore:MutableList<UserModel>
    private lateinit var binding: FragmentLeaderboardBinding

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLeaderboardBinding.inflate(inflater, container, false)
        val view = binding.root
        val leaderBoardViewModel = ViewModelProvider(requireActivity())[LeaderBoardViewModel::class.java]
        leaderBoardViewModel.loadData()
        binding.menu.setItemSelected(R.id.leaderboard)
        binding.menu.setOnItemSelectedListener {
            if (it== R.id.home)
            {
                findNavController().navigate(R.id.action_leaderboardFragment_to_mainMenuFragment)
            }
            if (it== R.id.profile)
            {
                findNavController().navigate(R.id.action_leaderboardFragment_to_profileFragment)
            }

        }
        leaderBoardViewModel._list.observe(viewLifecycleOwner, Observer {
            mathLeaderboard(it)
            binding.shimmerLayout.stopShimmer()
            binding.shimmerLayout.visibility = View.GONE
        })
        binding.buttonMath.setOnClickListener { mathLeaderboard(leaderBoardViewModel._list.value!!) }
        binding.buttonMemory.setOnClickListener { memoryLeaderboard(leaderBoardViewModel._list.value!!) }
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
        list.removeAt(0)
        list.removeAt(0)
        binding.leaderView.visibility = View.VISIBLE
        memoryLeaderAdapter.differ.submitList(list)
        binding.leaderView.layoutManager = LinearLayoutManager(context)
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
        list.removeAt(0)
        list.removeAt(0)
        binding.leaderView.visibility = View.VISIBLE
        leaderAdapter.differ.submitList(list)
        binding.leaderView.layoutManager = LinearLayoutManager(context)
        binding.leaderView.adapter = leaderAdapter


    }
}