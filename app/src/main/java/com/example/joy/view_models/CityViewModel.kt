package com.example.joy.view_models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.joy.api.JoyRepository
import com.example.joy.api.NetworkResponse
import com.example.joy.models.City
import com.example.joy.models.UserModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CityViewModel @Inject constructor(private val joyRepository: JoyRepository) : ViewModel() {

    val cities = MutableLiveData<ArrayList<City?>>()
    val loading = MutableLiveData<Boolean>()
    val success = MutableLiveData<Boolean>()

    init {
        getCities()
    }

    private fun getCities() {
        loading.value = true
       /* viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = joyRepository.getCities()
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    responseBody?.cities?.let { data ->
                        withContext(Dispatchers.Main) {
                            if (data.isNotEmpty()) cities.value = data
                            else success.value = false
                        }
                    } ?: run { withContext(Dispatchers.Main) { success.value = false } }
                }
            } catch (exception: Exception) {
                withContext(Dispatchers.Main) { success.value = false }
            } finally {
                withContext(Dispatchers.Main) { loading.value = false }
            }
        }*/

        viewModelScope.launch {
           when(val response = joyRepository.getCities()){
                is NetworkResponse.Success -> {
                    response.data?.cities?.let {
                        cities.value = it
                        loading.value=false
                    }
                }

                is NetworkResponse.Error -> {
                    success.value = false
                }
            }
        }
    }
}
