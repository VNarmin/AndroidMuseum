package com.example.joy.api

import com.example.joy.models.CityResponse
import com.example.joy.models.MuseumResponse
import com.example.joy.models.RegionResponse
import com.example.joy.utilities.Constants.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Service {
    @GET("apiv2/service/museum/cities")
    suspend fun getCities(@Query("apiKey") key : String = API_KEY) : Response < CityResponse >

    @GET("apiv2/service/museum/cities")
    suspend fun getRegions(@Query("apiKey") key : String = API_KEY, @Query("city") city : String) : Response < RegionResponse >

    @GET("apiv2/service/museum")
    suspend fun getMuseums(@Query("city") city : String, @Query("district") district : String, @Query("apiKey") key : String = API_KEY) : Response < MuseumResponse >
}

