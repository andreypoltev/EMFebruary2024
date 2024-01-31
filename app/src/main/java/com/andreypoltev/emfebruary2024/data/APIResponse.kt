package com.andreypoltev.emfebruary2024.data


import com.andreypoltev.emfebruary2024.data.model.Item
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class APIResponse(
    @SerialName("items")
    val items: List<Item> = listOf()
)