package com.example.joy.api

sealed class NetworkResponse<T:Any>(val data: T?, val message: String?) {

    class Success<T:Any>(data: T) : NetworkResponse<T>(data, null)

    class Error<T:Any>(message: String) : NetworkResponse<T>(null, message)

    class Loading<Nothing : Any> : NetworkResponse<Nothing>(null, null)


}