package com.example.joy.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.joy.databinding.ItemMuseumBinding
import com.example.joy.models.Museum

class MuseumAdapter : RecyclerView.Adapter < MuseumAdapter.MuseumViewHolder > () {
    private val museums = arrayListOf < Museum? > ()

    inner class MuseumViewHolder(val binding : ItemMuseumBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent : ViewGroup, viewType : Int) : MuseumViewHolder {
        val view = ItemMuseumBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MuseumViewHolder(view)
    }

    override fun getItemCount() : Int {
        return museums.size
    }

    override fun onBindViewHolder(holder : MuseumViewHolder, position : Int) {
        val museum = museums[position]
        holder.binding.museum = museum
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateMuseums(new : ArrayList < Museum? >) {
        museums.clear()
        museums.addAll(new)
        notifyDataSetChanged()
    }
}