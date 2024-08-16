package com.techne.trackerapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [User::class],
    version = 2,
    exportSchema = false
)
@TypeConverters()
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}