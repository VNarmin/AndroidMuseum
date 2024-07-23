package com.example.joy.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.joy.models.Soz
import kotlinx.coroutines.flow.Flow

@Dao
interface SozDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSoz(soz: Soz)

    @Query("select * from soz_table")
    fun getAllSoz(): Flow<List<Soz>>

    @Update
    fun updateSoz(soz: Soz)

}