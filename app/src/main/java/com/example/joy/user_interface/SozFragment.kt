package com.example.joy.user_interface

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.joy.adapters.SozAdapter
import com.example.joy.base.BaseFragment
import com.example.joy.databinding.FragmentSozBinding
import com.example.joy.models.Soz
import com.example.joy.view_models.SozViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SozFragment : BaseFragment<FragmentSozBinding>(FragmentSozBinding::inflate) {

    private val viewModel by viewModels<SozViewModel>()
    private val sozAdapter = SozAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvWords.adapter = sozAdapter
        viewModel.getWords()
        observeData()

        binding.buttonAdd.setOnClickListener {
            addWord()
        }

    }

    private fun observeData() {
        viewModel.wordList.observe(viewLifecycleOwner) {
            sozAdapter.updateList(it)
        }
    }

    private fun addWord() {

        val ing = binding.editTextNg.text.toString().trim()
        val turk = binding.editTextTurk.text.toString().trim()

        viewModel.addWord(Soz(ingilisce = ing, turkce = turk))
    }

}