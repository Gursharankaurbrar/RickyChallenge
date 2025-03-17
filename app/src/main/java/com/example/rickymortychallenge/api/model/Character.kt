package com.example.rickymortychallenge.api.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Entity(tableName = "characters")
@JsonClass(generateAdapter = true)
data class Character(
    @Json(name = "created")
    var created: String?,
    //@Json(name = "episode")
   // var episode: List<String?>?,
    @Json(name = "gender")
    var gender: String?,
    @Json(name = "id")
    @PrimaryKey(autoGenerate = false)
    var id: Int?,
    @Json(name = "image")
    var image: String?,
    //@Json(name = "location")
    //var location: Location?,
    @Json(name = "name")
    var name: String?,
    //@Json(name = "origin")
    //var origin: Origin?,
    @Json(name = "species")
    var species: String?,
    @Json(name = "status")
    var status: String?,
    @Json(name = "type")
    var type: String?,
    @Json(name = "url")
    var url: String?
)