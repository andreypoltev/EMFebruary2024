package com.andreypoltev.emfebruary2024.domain

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Query("SELECT * FROM User")
    fun flowAllUsers(): Flow<List<User>>

    @Query("SELECT COUNT(*) FROM User")
    fun flowUserCount(): Flow<Int>

    @Insert
    fun insertUser(user: User)

    @Delete
    fun deleteUser(user: User)

    @Query("DELETE FROM User")
    suspend fun clearTable()

}