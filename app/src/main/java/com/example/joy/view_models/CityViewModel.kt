package com.example.joy.view_models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.joy.api.JoyRepository
import com.example.joy.api.NetworkResponse
import com.example.joy.models.City
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CityViewModel @Inject constructor(private val joyRepository: JoyRepository) : ViewModel() {

    val uiState = MutableLiveData<CityUiState>()

    init {
        getCities()
    }

    private fun getCities() {
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

               response.data?.cities?.let {
                         cities.value = it
                         loading.value=false
                     }

                       success.value = false
         }*/

        viewModelScope.launch {
            joyRepository.getCities().collectLatest {
                when (it) {
                    is NetworkResponse.Loading -> {
                        uiState.value = CityUiState.Loading
                    }

                    is NetworkResponse.Success -> {
                        it.data?.cities?.let {
                            uiState.value = CityUiState.CityList(it)
                        }

                    }

                    is NetworkResponse.Error -> {
                        uiState.value = CityUiState.Error(it.message ?: "Error")
                    }
                }
            }
        }
    }
}

sealed class CityUiState {
    data class CityList(val cities: ArrayList<City?>) : CityUiState()
    object Loading : CityUiState()
    data class Error(val message: String = "") : CityUiState()
}
