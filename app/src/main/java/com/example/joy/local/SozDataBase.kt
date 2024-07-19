package com.example.joy.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.joy.models.Soz

@Database(entities = [Soz::class], version = 1)
abstract class SozDataBase : RoomDatabase() {

    abstract fun getDao(): SozDAO
}