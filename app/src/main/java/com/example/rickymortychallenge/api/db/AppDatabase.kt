package com.example.rickymortychallenge.api.db

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.rickymortychallenge.api.model.Character


@Database(entities = [Character:: class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase(){
    abstract  fun rickyDao(): RickyDao

    // COMPANION OBJECT
    companion object{
        @Volatile
        private var INSTANCE: AppDatabase ?=null

        fun getInstance(context: Context) : AppDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = androidx.room.Room.databaseBuilder(
                context.applicationContext,
                    AppDatabase::class.java,
                    "Ricky Challenge 5"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                instance
            }
        }

    }
}