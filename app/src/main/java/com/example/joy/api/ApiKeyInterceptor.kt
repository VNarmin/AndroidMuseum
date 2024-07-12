package com.example.joy.api

import com.example.joy.utilities.Constants.API_KEY
import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val url = request.url.newBuilder().addQueryParameter("apiKey", API_KEY).build()

        val newRequest = request.newBuilder().url(url).build()

        return chain.proceed(newRequest)
    }
}