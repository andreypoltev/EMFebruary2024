package com.andreypoltev.emfebruary2024.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Price(
    @SerialName("price")
    val price: String = "",
    @SerialName("discount")
    val discount: Int = 0,
    @SerialName("priceWithDiscount")
    val priceWithDiscount: String = "",
    @SerialName("unit")
    val unit: String = ""
)