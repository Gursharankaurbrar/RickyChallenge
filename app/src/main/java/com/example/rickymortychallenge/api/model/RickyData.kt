package com.example.rickymortychallenge.api.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RickyData(
    @Json(name = "info")
    var info: Info?,
    @Json(name = "results")
    var results: List<Character?>?
)