package com.example.rickymortychallenge.api.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Origin(
    @Json(name = "name")
    var name: String?,
    @Json(name = "url")
    var url: String?
)