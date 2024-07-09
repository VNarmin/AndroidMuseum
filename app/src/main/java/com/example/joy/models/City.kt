package com.example.joy.models

import com.google.gson.annotations.SerializedName

data class City(
    @SerializedName("cities")
    val city : String?,
    @SerializedName("slug")
    val slug : String?
)