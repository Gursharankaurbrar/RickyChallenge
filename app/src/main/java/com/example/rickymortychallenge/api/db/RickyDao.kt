package com.example.rickymortychallenge.api.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rickymortychallenge.api.model.Character

@Dao
interface RickyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll( movies: List<Character>)

    @Query("SELECT * FROM characters WHERE id = :id")
    fun getCharacterbyId(id: Int) : Character?

}