package com.example.joy.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.joy.databinding.ItemWordBinding
import com.example.joy.models.Soz

class SozAdapter : RecyclerView.Adapter<SozAdapter.SozViewHolder>() {

    private val wordList = arrayListOf<Soz>()

    inner class SozViewHolder(val binding: ItemWordBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SozViewHolder {
        val layout = ItemWordBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SozViewHolder(layout)
    }

    override fun getItemCount(): Int {
        return wordList.size
    }

    override fun onBindViewHolder(holder: SozViewHolder, position: Int) {
        val word = wordList[position]

        holder.binding.soz = word
    }

    fun updateList(word: List<Soz>) {
        wordList.clear()
        wordList.addAll(word)
        notifyDataSetChanged()
    }

}