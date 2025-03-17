package com.example.rickymortychallenge.api

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.rickymortychallenge.api.db.AppDatabase

import com.example.rickymortychallenge.api.model.Character
import com.example.rickymortychallenge.api.model.RickyData
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response

class RickyManager(database: AppDatabase) {
    private var _rickiesResponse = mutableStateOf<List<Character>>(emptyList())



    val rickyResponse: MutableState<List<Character>>
        @Composable get() = remember{
            _rickiesResponse
        }

    init{
        getRicky(database)
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun getRicky(database: AppDatabase) {
        val service = Api.retrofitService.getCharacters()

        service.enqueue(object : retrofit2.Callback<RickyData> {
            override fun onResponse(call: Call<RickyData>, response: Response<RickyData>) {
                if (response.isSuccessful) {
                    Log.i("Data", "Data is loaded successfully")

                    _rickiesResponse.value = (response.body()?.results ?: emptyList()) as List<Character>

                    Log.i("Data Stream", _rickiesResponse.value.toString())

                    GlobalScope.launch {
                        saveDataToDatabase(database = database, characters = _rickiesResponse.value)

                    }
                }
            }

            override fun onFailure(call: Call<RickyData>, t: Throwable) {
                Log.d("error", "Failed: ${t.message}")
            }
        })
    }

    private suspend fun saveDataToDatabase(database: AppDatabase, characters: List<Character>){
        database.rickyDao().insertAll(characters)
    }
}