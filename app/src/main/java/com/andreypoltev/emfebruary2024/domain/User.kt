package com.andreypoltev.emfebruary2024.domain

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey
    val phoneNumber: String,
    val name: String,
    val lastName: String,
)