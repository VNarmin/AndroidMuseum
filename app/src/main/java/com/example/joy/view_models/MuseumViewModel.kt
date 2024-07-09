package com.example.joy.view_models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.joy.api.Service
import com.example.joy.models.Museum
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MuseumViewModel @Inject constructor(private val service : Service) : ViewModel() {
    val museums = MutableLiveData < ArrayList < Museum? >> ()
    val loading = MutableLiveData < Boolean > ()
    val success = MutableLiveData < Boolean > ()

    fun getMuseums(city : String, region : String) {
        loading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = service.getMuseums(city, region)
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    responseBody?.museums?.let { data ->
                        withContext(Dispatchers.Main) {
                            if (data.isNotEmpty()) museums.value = data
                            else success.value = false
                        }
                    } ?: run { withContext(Dispatchers.Main) { success.value = false } }
                }
            } catch (exception : Exception) {
                withContext(Dispatchers.Main) { success.value = false }
            } finally {
                withContext(Dispatchers.Main) { loading.value = false }
            }
        }
    }
}