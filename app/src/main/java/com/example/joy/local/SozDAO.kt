package com.example.joy.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.joy.models.Soz

@Dao
interface SozDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSoz(soz: Soz)

    @Query("select * from soz_table")
    suspend fun getAllSoz(): List<Soz>

}