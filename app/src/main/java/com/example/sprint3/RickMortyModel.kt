package com.example.sprint3

import com.google.gson.annotations.SerializedName

data class RickMortyModel (@SerializedName("name") val name:String,
                           @SerializedName("image") val image:String) {
}