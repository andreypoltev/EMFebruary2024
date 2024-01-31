package com.andreypoltev.emfebruary2024.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Info(
    @SerialName("title")
    val title: String = "",
    @SerialName("value")
    val value: String = ""
)