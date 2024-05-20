package com.example.brainbooster.Adapter

import android.content.ContentValues
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.brainbooster.Activity.MainActivity
import com.example.brainbooster.R
import com.example.brainbooster.ViewModel.MenuViewModel


class ImageAdapter(private val context: Context,private val onItemClickListener: ((Int) -> Unit)) : RecyclerView.Adapter<ImageAdapter.ViewHolder>() {
    private val imageList = listOf(
        R.drawable.person1,
        R.drawable.person2,
        R.drawable.person3,
        R.drawable.person4,
        R.drawable.person5,
        R.drawable.person6,
        R.drawable.person7,
        R.drawable.person8,
        R.drawable.person9
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_image, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val imageResourceId = imageList[position]
        holder.imageView.setImageResource(imageResourceId)
        holder.itemView.setOnClickListener {
            onItemClickListener.invoke(position+1)
            Log.d(ContentValues.TAG, "ImageResourseId ${imageResourceId}")
            Log.d(ContentValues.TAG, "ImageId person${position+1}")
        }
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
    }
}