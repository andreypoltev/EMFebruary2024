package com.andreypoltev.emfebruary2024.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Feedback(
    @SerialName("count")
    val count: Int = 0,
    @SerialName("rating")
    val rating: Double = 0.0
)