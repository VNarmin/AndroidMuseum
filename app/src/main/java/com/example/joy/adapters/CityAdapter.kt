package com.example.joy.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.joy.databinding.ItemCityBinding
import com.example.joy.models.City

class CityAdapter : RecyclerView.Adapter < CityAdapter.CityViewHolder > () {
    private val cities = arrayListOf < City? > ()
    lateinit var onClick : (String) -> Unit

    inner class CityViewHolder(val binding : ItemCityBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent : ViewGroup, viewType : Int) : CityViewHolder {
        val view = ItemCityBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CityViewHolder(view)
    }

    override fun getItemCount() : Int {
        return cities.size
    }

    override fun onBindViewHolder(holder : CityViewHolder, position : Int) {
        val city = cities[position]
        holder.binding.city = city
        holder.binding.itemCity.setOnClickListener {
            city?.slug?.let { slug -> onClick(slug) }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateCities(new : ArrayList < City? > ) {
        cities.clear()
        cities.addAll(new)
        notifyDataSetChanged()
    }
}