package com.example.gymforlife.model

import java.io.Serializable

data class User(
    val name: String? = null,
    val email: String? = null,
    val uid: String? = null
) : Serializable

