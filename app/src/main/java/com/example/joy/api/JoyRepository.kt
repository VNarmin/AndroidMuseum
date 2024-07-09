package com.example.joy.api

import retrofit2.Response
import javax.inject.Inject

class JoyRepository @Inject constructor(private val service: Service) {

    suspend fun getCities() = safeApiRequest {
        service.getCities()
    }

    suspend fun getRegions(cityName: String) = safeApiRequest {
        service.getRegions(city = cityName)
    }


    private suspend fun <T> safeApiRequest(apiCall: suspend () -> Response<T>): NetworkResponse<T> {
        try {
            val response = apiCall.invoke()
            if (response.isSuccessful) {
                response.body()?.let {
                    return NetworkResponse.Success(it)
                } ?: return NetworkResponse.Error("Empty Response")
            } else {
                return NetworkResponse.Error(response.errorBody().toString())
            }

        } catch (e: Exception) {
            return NetworkResponse.Error(e.localizedMessage.toString())
        }
    }
}