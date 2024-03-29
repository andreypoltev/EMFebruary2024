package com.andreypoltev.emfebruary2024.data.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Entity
@Serializable
data class Item(
    @PrimaryKey
    @SerialName("id")
    val id: String = "",
    @SerialName("title")
    val title: String = "",
    @SerialName("subtitle")
    val subtitle: String = "",
    @SerialName("price")
    val price: Price = Price(),
    @SerialName("feedback")
    val feedback: Feedback? = Feedback(),
    @SerialName("tags")
    val tags: List<String> = listOf(),
    @SerialName("available")
    val available: Int = 0,
    @SerialName("description")
    val description: String = "",
    @SerialName("info")
    val info: List<Info> = listOf(),
    @SerialName("ingredients")
    val ingredients: String = ""
)