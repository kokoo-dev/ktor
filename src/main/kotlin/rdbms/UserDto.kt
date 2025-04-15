package com.kokoo.rdbms

import kotlinx.serialization.Serializable

@Serializable
data class UserDto(
    val name: String,
    val age: Int
)
