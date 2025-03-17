package com.example.rickymortychallenge.api.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rickymortychallenge.api.model.Character

@Dao
interface RickyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll( movies: List<Character>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacter(character: Character)

    @Delete
    suspend fun deleteCharacter(character: Character)

    @Query("SELECT * FROM characters WHERE id = :id")
    suspend fun getCharacterById(id: Int): Character?

    @Query("SELECT * FROM characters")
    suspend fun getAllCharacters(): List<Character>

}