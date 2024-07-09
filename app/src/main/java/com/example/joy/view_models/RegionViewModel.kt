package com.example.joy.view_models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.joy.api.Service
import com.example.joy.models.Region
import com.example.joy.utilities.Constants.API_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class RegionViewModel @Inject constructor(private val service : Service) : ViewModel() {
    val regions = MutableLiveData < ArrayList < Region? >> ()
    val loading = MutableLiveData < Boolean > ()
    val success = MutableLiveData < Boolean > ()

    fun getRegions(city : String) {
        loading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = service.getRegions(API_KEY, city)
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    responseBody?.regions?.let { data ->
                        withContext(Dispatchers.Main) {
                            if (data.isNotEmpty()) regions.value = data
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