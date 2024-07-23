package com.example.joy.api

import com.example.joy.local.SozDAO
import com.example.joy.models.CityResponse
import com.example.joy.models.Soz
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
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

     fun updateSoz(soz: Soz) = sozDAO.updateSoz(soz)


    private suspend fun <T : Any> safeApiRequest(apiCall: suspend () -> Response<T>): Flow<NetworkResponse<T>> =
        flow {
            emit(NetworkResponse.Loading())
            try {
                val response = apiCall.invoke()
                if (response.isSuccessful) {
                    response.body()?.let {
                        emit(NetworkResponse.Success(it))
                    } ?: emit(NetworkResponse.Error("Empty Response"))
                } else {
                    emit(NetworkResponse.Error(response.errorBody().toString()))
                }

            } catch (e: Exception) {
                emit(NetworkResponse.Error(e.localizedMessage.toString()))
            }
        }.flowOn(Dispatchers.IO)


}