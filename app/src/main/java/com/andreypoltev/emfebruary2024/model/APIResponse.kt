package com.andreypoltev.emfebruary2024.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class APIResponse(
    @SerialName("items")
    val items: List<Item> = listOf()
)