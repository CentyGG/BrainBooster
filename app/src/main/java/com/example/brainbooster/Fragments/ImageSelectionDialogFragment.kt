package com.example.brainbooster.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.brainbooster.Adapter.ImageAdapter
import com.example.brainbooster.R

class ImageSelectionDialogFragment : DialogFragment() {
    private lateinit var recyclerView: RecyclerView
    private val imageAdapter: ImageAdapter by lazy { ImageAdapter(requireContext()) { imageResourceId ->
        onImageSelectedListener?.invoke(imageResourceId.toString())
        dismiss()
    } }
    var onImageSelectedListener: ((String) -> Unit)? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.dialog_image_selection, container, false)

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
        recyclerView.adapter = imageAdapter



        return view
    }

}