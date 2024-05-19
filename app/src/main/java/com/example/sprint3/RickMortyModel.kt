package com.example.sprint3

import com.google.gson.annotations.SerializedName

data class RickMortyModel (@SerializedName("name") val name:String,
                           @SerializedName("image") val image:String,
                           @SerializedName("status") val status:String,
                           @SerializedName("species") val species:String,
                           @SerializedName("gender") val gender:String) {
}