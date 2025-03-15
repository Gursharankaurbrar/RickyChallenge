package com.example.rickymortychallenge.api

import com.example.rickymortychallenge.api.model.RickyData
import retrofit2.Call
import retrofit2.http.GET

interface RickyService {
    // endpoints
    @GET("character")
    fun getCharacters(): Call<RickyData>
}