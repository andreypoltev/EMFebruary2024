package com.andreypoltev.emfebruary2024.domain

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.andreypoltev.emfebruary2024.data.model.Item
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {

    @Query("SELECT COUNT(*) FROM Item")
    fun flowRowCount(): Flow<Int>
    @Query("SELECT * FROM Item")
    fun flowAllItems(): Flow<List<Item>>

    @Query("SELECT * FROM Item")
    suspend fun getAllItems(): List<Item>

    @Insert
    suspend fun insertItem(item: Item)

    @Delete
    suspend fun removeItem(item: Item)

}