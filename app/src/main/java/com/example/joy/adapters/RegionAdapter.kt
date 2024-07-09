package com.example.joy.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.joy.databinding.ItemRegionBinding
import com.example.joy.models.Region

class RegionAdapter : RecyclerView.Adapter < RegionAdapter.RegionViewHolder > () {
    private val regions = arrayListOf < Region? > ()
    lateinit var onClick : (String) -> Unit

    inner class RegionViewHolder(val binding : ItemRegionBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent : ViewGroup, viewType : Int) : RegionViewHolder {
        val view = ItemRegionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RegionViewHolder(view)
    }

    override fun getItemCount() : Int {
        return regions.size
    }

    override fun onBindViewHolder(holder : RegionViewHolder, position : Int) {
        val region = regions[position]
        holder.binding.region = region
        holder.binding.itemRegion.setOnClickListener {
            region?.slug?.let { slug -> onClick(slug) }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateRegions(new : ArrayList < Region? > ) {
        regions.clear()
        regions.addAll(new)
        notifyDataSetChanged()
    }
}