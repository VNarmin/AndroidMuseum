package com.example.joy.view_models

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.joy.api.JoyRepository
import com.example.joy.models.Soz
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
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

    fun updateWord(word: Soz) {
        viewModelScope.launch(Dispatchers.IO) {
            joyRepository.updateSoz(word)
            getWords()
        }
    }

    fun getWords() {
        viewModelScope.launch {
            joyRepository.getSoz().collectLatest {
                wordList.value = it
            }
        }
    }

    fun getFlow() {
        val flow1 = flow {
            emit(1)
            delay(5000)
            emit("dsasdas")
            emit(true)
        }
        viewModelScope.launch {
            flow1.collectLatest {
                Log.e("Flow1", it.toString())
            }
        }

    }

    fun getFlow2() {
        val flow2 = flow {
            emit(10)
            emit(11)
        }.map {
            it * it
        }

        viewModelScope.launch {
            flow2.collectLatest {


            }
        }
    }
}