package com.example.joy.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "soz_table")
data class Soz(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo("soz_turkce")
    val turkce: String,
    val ingilisce: String,
    val isFavorite: Boolean = false
)
