package com.example.brainbooster.presentation.leaderboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.brainbooster.domain.entity.UserModel
import com.example.brainbooster.databinding.ViewholderLeaderboardBinding


class LeaderAdapter : RecyclerView.Adapter<LeaderAdapter.ViewHolder>() {

    private lateinit var binding: ViewholderLeaderboardBinding

    inner class ViewHolder: RecyclerView.ViewHolder(binding.root)

    override fun getItemCount() =
        differ.currentList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val binding = ViewholderLeaderboardBinding.bind(holder.itemView)
        binding.titleTxt.text = differ.currentList[position].nickname

        val drawableResourceId:Int = binding.root.resources.getIdentifier(
            differ.currentList[position].imageid,
            "drawable",binding.root.context.packageName

        )
        Glide.with(binding.root.context).load(drawableResourceId).into(binding.pic)
        binding.rowTxt.text=(position+4).toString()
        binding.scoreTxt.text=differ.currentList[position].score_math.toString()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
            binding = ViewholderLeaderboardBinding.inflate(inflater,parent,false)
        return ViewHolder()
    }
    private val differCallback = object : DiffUtil.ItemCallback<UserModel>(){
        override fun areItemsTheSame(oldItem: UserModel, newItem: UserModel): Boolean {
            return oldItem.nickname==newItem.nickname
        }

        override fun areContentsTheSame(oldItem: UserModel, newItem: UserModel): Boolean {
            return oldItem==newItem
        }
    }
    val differ = AsyncListDiffer(this,differCallback)


}