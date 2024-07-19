package com.example.joy.api

import com.example.joy.local.SozDAO
import com.example.joy.models.CityResponse
import com.example.joy.models.Soz
import retrofit2.Response
import javax.inject.Inject

class JoyRepository @Inject constructor(private val service: Service, private val sozDAO: SozDAO) {

    suspend fun getCities() = safeApiRequest {
        service.getCities()
    }

    suspend fun getRegions(cityName: String) = safeApiRequest {
        service.getRegions(city = cityName)
    }

    suspend fun getapi(): Response<CityResponse> {
        return service.getCities()
    }

    suspend fun getapi2() = service.getCities()

    fun addSoz(soz: Soz) = sozDAO.insertSoz(soz)

    suspend fun getSoz() = sozDAO.getAllSoz()


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