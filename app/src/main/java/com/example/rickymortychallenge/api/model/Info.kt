package com.example.rickymortychallenge.api.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Info(
    @Json(name = "count")
    var count: Int?,
    @Json(name = "next")
    var next: String?,
    @Json(name = "pages")
    var pages: Int?,
    @Json(name = "prev")
    var prev: Any?
)