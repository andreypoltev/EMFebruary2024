package com.andreypoltev.emfebruary2024.domain

import androidx.room.TypeConverter
import com.andreypoltev.emfebruary2024.data.model.Feedback
import com.andreypoltev.emfebruary2024.data.model.Info
import com.andreypoltev.emfebruary2024.data.model.Price
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class TypeConverters {

    @TypeConverter
    fun toPrice(price: String) = Json.decodeFromString<Price>(price)

    @TypeConverter
    fun fromPrice(price: Price) = Json.encodeToString(price)

    @TypeConverter
    fun toFeedback(feedback: String) = Json.decodeFromString<Feedback>(feedback)

    @TypeConverter
    fun fromFeedback(feedback: Feedback) = Json.encodeToString(feedback)

    @TypeConverter
    fun toListOfStrings(string: String) = Json.decodeFromString<List<String>>(string)

    @TypeConverter
    fun fromListOfStrings(strings: List<String>) = Json.encodeToString(strings)

    @TypeConverter
    fun toListOfInfo(info: String) = Json.decodeFromString<List<Info>>(info)

    @TypeConverter
    fun fromListOfInfo(listOfInfo: List<Info>) = Json.encodeToString(listOfInfo)




}