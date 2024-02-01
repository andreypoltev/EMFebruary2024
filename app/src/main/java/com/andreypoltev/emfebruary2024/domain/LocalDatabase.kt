package com.andreypoltev.emfebruary2024.domain

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.andreypoltev.emfebruary2024.data.model.Item

@Database(version = 1, entities = [User::class, Item::class])
@TypeConverters(com.andreypoltev.emfebruary2024.domain.TypeConverters::class)
abstract class LocalDatabase : RoomDatabase() {

    abstract val userDao: UserDao
    abstract val itemDao: ItemDao

    companion object {
        const val DATABASE_NAME = "local_db"
    }
}