package com.example.joy.view_models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.joy.api.JoyRepository
import com.example.joy.models.Soz
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SozViewModel @Inject constructor(
    private val joyRepository: JoyRepository
) : ViewModel() {

    val wordList = MutableLiveData<List<Soz>>()


    fun addWord(word: Soz) {
        viewModelScope.launch(Dispatchers.IO) {
            joyRepository.addSoz(word)
            getWords()
        }
    }

    fun getWords() {
        viewModelScope.launch {
            val sozler = joyRepository.getSoz()

            wordList.value = sozler
        }
    }
}