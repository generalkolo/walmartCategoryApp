package com.miu.cs473.assignement4lesson5

import java.io.Serializable

data class User(
    val firstName: String,
    val lastName: String,
    val username: String,
    val password: String
) : Serializable
