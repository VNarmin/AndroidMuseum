package com.example.joy.models

import com.google.gson.annotations.SerializedName

data class Region(
    @SerializedName("cities")
    val region: String?,
    @SerializedName("slug")
    val slug: String?
)