package com.example.rickymortychallenge.api

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

import com.example.rickymortychallenge.api.model.Character
import com.example.rickymortychallenge.api.model.RickyData
import retrofit2.Call
import retrofit2.Response

class RickyManager {
    private var _rickiesResponse = mutableStateOf<List<Character>>(emptyList())



    val rickyResponse: MutableState<List<Character>>
        @Composable get() = remember{
            _rickiesResponse
        }

    init{
        getRicky()
    }

    private fun getRicky() {
        val service = Api.retrofitService.getCharacters() // Correct API call

        service.enqueue(object : retrofit2.Callback<RickyData> {
            override fun onResponse(call: Call<RickyData>, response: Response<RickyData>) {
                if (response.isSuccessful) {
                    Log.i("Data", "Data is loaded successfully")

                    // Update state properly
                    _rickiesResponse.value = (response.body()?.results ?: emptyList()) as List<Character>

                    Log.i("Data Stream", _rickiesResponse.value.toString())
                }
            }

            override fun onFailure(call: Call<RickyData>, t: Throwable) {
                Log.d("error", "Failed: ${t.message}")
            }
        })
    }

}