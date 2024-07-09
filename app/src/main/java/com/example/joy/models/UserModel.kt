package com.example.joy.models

import com.example.joy.view_models.TYPE

data class UserModel(
    val id: Int,
    val name: String,
    val type: TYPE
)
